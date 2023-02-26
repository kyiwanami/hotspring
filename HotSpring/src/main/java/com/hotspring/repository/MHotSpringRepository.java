package com.hotspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotspring.entity.MHotSpringEntity;

@Repository
public interface MHotSpringRepository extends JpaRepository <MHotSpringEntity , Integer> {
	
}
