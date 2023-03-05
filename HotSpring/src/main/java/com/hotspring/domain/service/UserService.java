package com.hotspring.domain.service;

import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotspring.entity.MUserEntity;
import com.hotspring.repository.MUSerRepository;

@Service
public class UserService {

	@Autowired
	MUSerRepository uSerRepository;

	/**
	 * ユーザー登録する。
	 */
	public void createUser(String userName, boolean openFlag) {

		MUserEntity entity = new MUserEntity();
		entity.setUserName(userName);
		entity.setOpenFlag(BooleanUtils.toIntegerObject(openFlag).toString());
		uSerRepository.save(entity);
	}

}
