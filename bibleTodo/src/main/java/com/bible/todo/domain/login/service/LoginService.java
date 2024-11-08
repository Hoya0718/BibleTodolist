package com.bible.todo.domain.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bible.todo.domain.login.mapper.LoginMapper;

@Service
public class LoginService {
	private final LoginMapper loginMapper;
	
	@Autowired
	public LoginService(LoginMapper loginMapper) {
		this.loginMapper = loginMapper;
	}

}
