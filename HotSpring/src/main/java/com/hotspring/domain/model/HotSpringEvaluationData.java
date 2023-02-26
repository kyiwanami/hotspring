package com.hotspring.domain.model;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class HotSpringEvaluationData {

	private int hotSpringId;
	private String hotSpringName;
	private String hotSpringConcept;
	private String evaluation;
	private String location;
	private String url;
	private String imageS3url;
	private MultipartFile image;
	private int evalationId;
	private Date visitDate;
	private String overallRank;
	private String capaciousnessRank;
	private String varietyRank;
	private String priceRank;
}
