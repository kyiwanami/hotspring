package com.hotspring.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotspring.entity.MHotSpring;
import com.hotspring.repository.HotSpringMapper;

@Service
public class HotSpringService {

	@Autowired
	private HotSpringMapper hotSpringMapper;
	
	/**
	 * 温泉リストを全件取得.
	 * @return 温泉リスト
	 */
	public List<MHotSpring> selectHotspring() {
		return hotSpringMapper.selectHotSpring();
	}
	
	/**
	 * 温泉のイメージファイルを取得.
	 * @param 温泉ID
	 * @return イメージファイル
	 */
	public byte[] selectImage(int hotSpringId) {
		return hotSpringMapper.selectImage(hotSpringId);
	}
	
}
