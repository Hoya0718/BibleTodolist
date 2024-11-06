package com.bible.todo.domain.user.service;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.bible.todo.domain.bible.mapper.BibleMapper;
import com.bible.todo.domain.user.mapper.UserMapper;
import com.bible.todo.domain.user.vo.UserVo;

import lombok.extern.log4j.Log4j2;

@Service
public class UserService{
	private final UserMapper userMapper;
	
	@Autowired
	public UserService(UserMapper userMapper) {
		this.userMapper = userMapper;
	}
	
	//회원가입
	public void signUp(UserVo userVo) {
		userMapper.signUp(userVo);
	}
	
	//로그인
	public void signIn(UserVo userVo) {
		return;
	}
	
	//로그인 후 비밀번호 변경
	public String checkPw(String user_id) {
		return userMapper.checkPw(user_id);
	}
	
	//비밀번호 찾기
	public String findPw(String user_id) {
		return userMapper.findPw(user_id);
	}
	
	//비밀번호 변경
	public ArrayList<String> editPw(String user_id, String user_pw) {
		return userMapper.editPw(user_id, user_pw);
	}
}
