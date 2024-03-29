package com.hotspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotspring.entity.MUserEntity;

@Repository
public interface MUSerRepository extends JpaRepository<MUserEntity, String> {

}
