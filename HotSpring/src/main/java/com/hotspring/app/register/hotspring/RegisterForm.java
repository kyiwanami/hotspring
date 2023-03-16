package com.hotspring.app.register.hotspring;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class RegisterForm {

	private int hotSpringId;
	@NotEmpty
	@Size(max = 50)
	private String hotSpringName;
	@Size(max = 300)
	private String hotSpringConcept;
	@Size(max = 50)
	private String location;
	@Size(max = 300)
	private String url;
	private String imageS3url;
	private MultipartFile image;
	private String evalationId;
	@NotEmpty
	@Size(max = 300)
	private String evaluation;
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date visitDate;
	@NotEmpty
	@Size(max = 1)
	private String overallRank;
	@NotEmpty
	@Size(max = 1)
	private String capaciousnessRank;
	@NotEmpty
	@Size(max = 1)
	private String varietyRank;
	@NotEmpty
	@Size(max = 1)
	private String priceRank;
}
