package com.hotspring.domain.service;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Service
@Slf4j
public class AwsService {

	@Autowired
	private S3Client s3Client;

	@Value("${aws.s3.bucket}")
	private String bucket;

	@Value("${aws.s3.key-pattern}")
	private String keypattern;

	/**
	 * S3からオブジェクトを取得する.
	 * 
	 * @param key オブジェクトキー
	 * @return オブジェクト
	 * @throws IOException
	 */
	public byte[] getObject(String key) throws IOException {

		GetObjectRequest getObjectRequest = GetObjectRequest.builder().bucket(bucket).key(key).build();

		return s3Client.getObject(getObjectRequest).readAllBytes();
	}

	/**
	 * S3にオブジェクトを配置する.
	 * 
	 * @throws IOException
	 */
	public void putObject(String key, InputStream stream) throws IOException {

		PutObjectRequest objectRequest = PutObjectRequest.builder().bucket(bucket).key(key).build();

		try {
			s3Client.putObject(objectRequest, RequestBody.fromBytes(stream.readAllBytes()));
		} catch (AwsServiceException | SdkClientException | IOException e) {

			log.error("S3アップロードに失敗しました。");
			throw e;
		}
	}

}
