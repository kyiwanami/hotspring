package com.hotspring.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
public class AwsConfig {

	private static final String REGION = "ap-northeast-1";

	@Value("${aws.s3.endpoint-url}")
	private String endpointUrl;

	@Bean
	public AmazonS3 amazonS3() {

		EndpointConfiguration endpointConfiguration = new EndpointConfiguration(endpointUrl, REGION);

		final AmazonS3 client = AmazonS3ClientBuilder.standard().withEndpointConfiguration(endpointConfiguration)
				.build();

		return client;
	}

}
