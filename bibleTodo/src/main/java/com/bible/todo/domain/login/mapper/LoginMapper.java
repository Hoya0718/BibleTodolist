package com.bible.todo.domain.login.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.bible.todo.domain.user.vo.UserVo;


@Mapper
public interface LoginMapper {
	String findByUserId(UserVo userVo);
}
