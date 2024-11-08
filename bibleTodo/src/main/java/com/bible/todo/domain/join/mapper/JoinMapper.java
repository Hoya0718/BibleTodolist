package com.bible.todo.domain.join.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.bible.todo.domain.user.vo.UserVo;

@Mapper
public interface JoinMapper {
	void joinProcess(UserVo uservo);
}