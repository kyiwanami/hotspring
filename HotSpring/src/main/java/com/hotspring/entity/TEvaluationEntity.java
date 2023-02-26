package com.hotspring.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Data
@Entity
@Table(name = "T_EVALUATION")
public class TEvaluationEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "EVALATION_ID")
	private int evalationId;

	@Column(name = "HOT_SPRING_ID")
	private int hotSpringId;

	@Column(name = "EVALUATION")
	private String evaluation;

	@Column(name = "VISIT_DATE")
	@Temporal(TemporalType.DATE)
	private Date visitDate;

	@Column(name = "OVERALL_RANK")
	private String overallRank;

	@Column(name = "CAPACIOUSNESS_RANK")
	private String capaciousnessRank;

	@Column(name = "VARIETY_RANK")
	private String varietyRank;

	@Column(name = "PRICE_RANK")
	private String priceRank;
}
