package com.hotspring.domain.model;

import lombok.Data;

@Data
public class UserData {

	private String sub;
	private String userName;
	private String password;
	private String emailAddress;
	private boolean openFlag;

}
