package com.hotspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotspring.entity.TEvaluationEntity;

@Repository
public interface TEvaluationRepository extends JpaRepository <TEvaluationEntity , Integer> {
	
}
