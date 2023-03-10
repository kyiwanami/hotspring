package com.hotspring.config;

import java.net.URI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClientBuilder;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class AwsConfig {

	@Value("${aws.s3.endpoint-url}")
	private String endpointUrl;

	@Bean
	public S3Client s3Client() {

		S3Client s3Client = S3Client.builder().region(Region.AP_NORTHEAST_1).endpointOverride(URI.create(endpointUrl))
				.forcePathStyle(true).build();

		return s3Client;
	}

	@Bean
	public AWSCognitoIdentityProvider cognitoClient() {

		AWSCognitoIdentityProvider cognitoClient = AWSCognitoIdentityProviderClientBuilder.standard()
				.withRegion(Regions.AP_NORTHEAST_1).build();

		return cognitoClient;
	}

}
