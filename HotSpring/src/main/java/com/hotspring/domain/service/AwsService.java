package com.hotspring.domain.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.model.AdminCreateUserRequest;
import com.amazonaws.services.cognitoidp.model.AdminInitiateAuthRequest;
import com.amazonaws.services.cognitoidp.model.AdminInitiateAuthResult;
import com.amazonaws.services.cognitoidp.model.AdminRespondToAuthChallengeRequest;
import com.amazonaws.services.cognitoidp.model.AuthFlowType;

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
	 * ユーザーを作成する.
	 * 
	 * @param userName ユーザー名
	 * @param password パスワード
	 * @throws Exception
	 */
	public void createUser(String userName, String password) throws Exception {

		try {
			// ユーザー登録
			AdminCreateUserRequest adminCreateUserRequest = new AdminCreateUserRequest();
			adminCreateUserRequest.withUserPoolId(cognitiUserPoolId).withUsername(userName)
					.withTemporaryPassword(password);
			cognitoClient.adminCreateUser(adminCreateUserRequest);

			// トークン取得
			Map<String, String> authParameters = new HashMap<>();
			authParameters.put("USERNAME", userName);
			authParameters.put("PASSWORD", password);

			AdminInitiateAuthRequest adminInitiateAuthRequest = new AdminInitiateAuthRequest();
			adminInitiateAuthRequest.withAuthFlow(AuthFlowType.ADMIN_NO_SRP_AUTH).withUserPoolId(cognitiUserPoolId)
					.withClientId(cognitoClientId).withAuthParameters(authParameters);

			AdminInitiateAuthResult initResult = cognitoClient.adminInitiateAuth(adminInitiateAuthRequest);

			// 一時パスワード更新
			Map<String, String> challengeResponses = new HashMap<>();
			challengeResponses.put("USERNAME", userName);
			challengeResponses.put("NEW_PASSWORD", password);

			AdminRespondToAuthChallengeRequest request = new AdminRespondToAuthChallengeRequest();
			request.withChallengeName(initResult.getChallengeName()).withUserPoolId(cognitiUserPoolId)
					.withClientId(cognitoClientId).withSession(initResult.getSession())
					.withChallengeResponses(challengeResponses);

		} catch (Exception e) {
			log.error("Cognitoユーザー登録に失敗しました。");
			throw e;
		}
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
