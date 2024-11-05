package com.bible.todo.domain.user.service;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bible.todo.domain.bible.mapper.BibleMapper;
import com.bible.todo.domain.user.mapper.UserMapper;
import com.bible.todo.domain.user.vo.UserVo;

@Service
public class UserService {
	private final UserMapper userMapper;
	
	@Autowired
	public UserService(UserMapper userMapper) {
		this.userMapper = userMapper;
	}
	
	public void signUp(UserVo userVo) {
		userMapper.signUp(userVo);
	}
	
	public Map<String, Object> signIn(String user_id, String user_pw) {
		return userMapper.signIn(user_id, user_pw);
	}
	
	public String checkPw(String user_id) {
		return userMapper.checkPw(user_id);
	}
	
	public String findPw(String user_id) {
		return userMapper.findPw(user_id);
	}
	
	public ArrayList<String> editPw(String user_id, String user_pw) {
		return userMapper.editPw(user_id, user_pw);
	}
}
