package com.hotspring.app.auth;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class AuthForm {

	@NotEmpty
	@Size(max = 10)
	String userName;

	@NotEmpty
	@Size(max = 10)
	String password;
}
