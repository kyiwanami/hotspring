package com.hotspring.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotspring.entity.MKey;
import com.hotspring.repository.KeyMapper;

@Service
public class KeyService {

	@Autowired
	KeyMapper keyMapper;
	
	public List<MKey> selectKey() {
		return keyMapper.selectKey();
	}
	
}
