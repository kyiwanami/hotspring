package com.hotspring.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "M_USER")
public class MUserEntity {

	@Id
	@Column(name = "SUB")
	private String sub;

	@Column(name = "USER_NAME")
	private String userName;

	@Column(name = "EMAIL_ADDRESS")
	private String emailAddress;

	@Column(name = "OPEN_FLAG")
	private String openFlag;

}