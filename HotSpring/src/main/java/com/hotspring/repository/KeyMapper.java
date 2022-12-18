package com.hotspring.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.hotspring.entity.MKey;

@Mapper
public interface KeyMapper {

	@Select("SELECT * FROM M_KEY WHERE DUPLICATE_FLG = '0'")
    public List<MKey> selectKey();
}
