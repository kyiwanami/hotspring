package com.hotspring.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.hotspring.entity.MHotSpring;

@Mapper
public interface HotSpringMapper {

	@Select("SELECT * FROM M_HOT_SPRING")
    public List<MHotSpring> selectHotSpring();
	
	@Select("SELECT PHOTO_IMAGE FROM M_HOT_SPRING WHERE HOT_SPRING_ID = #{hotSpringId}")
    public byte[] selectImage(int hotSpringId);
	
}
