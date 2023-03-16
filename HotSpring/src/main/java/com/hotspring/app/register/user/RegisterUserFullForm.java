package com.hotspring.app.register.user;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class RegisterUserFullForm {

	@NotEmpty
	@Size(max = 10)
	private String temporaryPassword;

	@NotEmpty
	@Size(max = 10)
	private String password;
}
