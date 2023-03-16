package com.hotspring.app.login;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class LoginForm {

	@NotEmpty
	@Size(max = 256)
	private String emailAddress;

	@NotEmpty
	@Size(max = 10)
	private String password;
}
