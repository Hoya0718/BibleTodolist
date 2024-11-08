package com.bible.todo.domain.join.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bible.todo.domain.join.mapper.JoinMapper;
import com.bible.todo.domain.user.vo.UserVo;

@Service
public class JoinService {
	private final JoinMapper joinMapper;
	
	@Autowired
	public JoinService(JoinMapper joinMapper) {
		this.joinMapper = joinMapper;
	}
	
	@Autowired
	public BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public void joinProcess(UserVo userVo) {
		String encodedPassword  = bCryptPasswordEncoder.encode(userVo.getUser_pw());
		userVo.setUser_pw(encodedPassword);
		joinMapper.joinProcess(userVo);
	}
}
