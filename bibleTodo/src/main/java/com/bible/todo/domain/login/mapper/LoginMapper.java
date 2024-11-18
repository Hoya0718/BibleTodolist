package com.bible.todo.domain.login.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.bible.todo.domain.user.vo.UserVo;


@Mapper
public interface LoginMapper {
	Map<String, Object> findByUserId(UserVo userVo);
}
