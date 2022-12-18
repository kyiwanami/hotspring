package com.hotspring.entity;

import com.hotspring.Constants.key;

import lombok.Data;

@Data
public class MKey {
	private Integer keyId;
	private String keyName;
	private key majorOrMinor;
}
