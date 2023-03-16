package com.hotspring.domain.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotspring.domain.model.UserData;
import com.hotspring.entity.MUserEntity;
import com.hotspring.repository.MUSerRepository;
import com.hotspring.util.BooleanUtil;

@Service
public class UserService {

	@Autowired
	private MUSerRepository userRepository;

	/**
	 * ユーザー登録する。
	 * 
	 * @param userData ユーザーデータ
	 */
	public void createUser(UserData userData) {

		MUserEntity entity = new MUserEntity();
		BeanUtils.copyProperties(userData, entity);
		entity.setOpenFlag(BooleanUtil.BoolToStr(userData.isOpenFlag()));
		userRepository.save(entity);
	}

	/**
	 * ユーザー取得する。
	 * 
	 * @param userData ユーザーデータ
	 * @return ユーザー名
	 * @throws Exception
	 */
	public String getUser(String sub) throws Exception {

		return userRepository.findById(sub).orElseThrow(() -> new Exception("ユーザーが存在しません。")).getUserName();
	}
}
