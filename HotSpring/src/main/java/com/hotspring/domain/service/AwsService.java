package com.hotspring.domain.service;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.util.IOUtils;

@Service
public class AwsService {

	@Autowired
	private AmazonS3 amazonS3;

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

		S3Object object = amazonS3.getObject(bucket, String.format(keypattern, key));
		return IOUtils.toByteArray(object.getObjectContent());
	}

	/**
	 * S3にオブジェクトを配置する.
	 * 
	 */
	public void putObject(String key, InputStream stream) {

		amazonS3.putObject(bucket, String.format(keypattern, key), stream, new ObjectMetadata());
	}

}
