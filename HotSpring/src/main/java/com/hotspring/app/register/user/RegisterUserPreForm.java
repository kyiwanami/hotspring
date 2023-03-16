package com.hotspring.app.register.user;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class RegisterUserPreForm {

	@NotEmpty
	@Size(max = 256)
	private String emailAddress;

	@NotEmpty
	@Size(max = 10)
	private String userName;

	private boolean openFlag;
}
