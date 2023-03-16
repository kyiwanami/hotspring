package com.hotspring.domain.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.model.AdminCreateUserRequest;
import com.amazonaws.services.cognitoidp.model.AdminCreateUserResult;
import com.amazonaws.services.cognitoidp.model.AdminInitiateAuthRequest;
import com.amazonaws.services.cognitoidp.model.AuthFlowType;
import com.hotspring.domain.model.UserData;

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
	private String s3bucket;

	@Value("${aws.s3.key-pattern}")
	private String s3keypattern;

	@Autowired
	private AWSCognitoIdentityProvider cognitoClient;

	@Value("${aws.cognito.user-pool-id}")
	private String cognitiUserPoolId;

	@Value("${aws.cognito.client-id}")
	private String cognitoClientId;

	/**
	 * S3からオブジェクトを取得する.
	 * 
	 * @param key オブジェクトキー
	 * @return オブジェクト
	 * @throws IOException
	 */
	public byte[] getObject(String key) throws IOException {

		GetObjectRequest getObjectRequest = GetObjectRequest.builder().bucket(s3bucket).key(key).build();

		return s3Client.getObject(getObjectRequest).readAllBytes();
	}

	/**
	 * S3にオブジェクトを配置する.
	 * 
	 * @throws IOException
	 */
	public void putObject(String key, InputStream stream) throws IOException {

		PutObjectRequest objectRequest = PutObjectRequest.builder().bucket(s3bucket).key(key).build();

		try {
			s3Client.putObject(objectRequest, RequestBody.fromBytes(stream.readAllBytes()));
		} catch (AwsServiceException | SdkClientException | IOException e) {

			log.error("S3アップロードに失敗しました。");
			throw e;
		}
	}

	/**
	 * ユーザーを仮作成する.
	 * 
	 * @param userData ユーザーデータ
	 */
	public String adminCreateUser(UserData userData) {

		// ユーザー登録リクエスト
		AdminCreateUserRequest adminCreateUserRequest = new AdminCreateUserRequest();
		adminCreateUserRequest.withUserPoolId(cognitiUserPoolId).withUsername(userData.getEmailAddress());

		// ユーザー登録リクエスト送信
		AdminCreateUserResult result = cognitoClient.adminCreateUser(adminCreateUserRequest);
		String sub = result.getUser().getAttributes().stream().filter(attr -> StringUtils.equals(attr.getName(), "sub"))
				.findFirst().orElseThrow().getValue();

		return sub;
	}

	/**
	 * ユーザーを認証する.
	 * 
	 * @param userName ユーザー名
	 * @param password パスワード
	 * @throws Exception
	 */
	public void authUser(String userName, String password) throws Exception {

		try {

			// トークン取得
			Map<String, String> authParameters = new HashMap<>();
			authParameters.put("USERNAME", userName);
			authParameters.put("PASSWORD", password);

			AdminInitiateAuthRequest adminInitiateAuthRequest = new AdminInitiateAuthRequest();
			adminInitiateAuthRequest.withAuthFlow(AuthFlowType.ADMIN_NO_SRP_AUTH).withUserPoolId(cognitiUserPoolId)
					.withClientId(cognitoClientId).withAuthParameters(authParameters);

			cognitoClient.adminInitiateAuth(adminInitiateAuthRequest);

		} catch (Exception e) {
			log.error("Cognitoユーザー認証に失敗しました。");
			throw e;
		}
	}

}
