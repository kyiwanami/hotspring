package com.hotspring.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "M_HOT_SPRING")
public class MHotSpringEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "HOT_SPRING_ID")
	private int hotSpringId;

	@Column(name = "HOT_SPRING_NAME")
	private String hotSpringName;

	@Column(name = "HOT_SPRING_CONCEPT")
	private String hotSpringConcept;

	@Column(name = "LOCATION")
	private String location;

	@Column(name = "URL")
	private String url;

	@Column(name = "IMAGE_S3_URL")
	private String imageS3Url;

}