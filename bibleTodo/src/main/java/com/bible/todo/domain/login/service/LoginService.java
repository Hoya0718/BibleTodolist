package com.bible.todo.domain.login.service;

import org.springframework.stereotype.Service;

import com.bible.todo.domain.login.dto.LoginDTO;
import com.bible.todo.domain.login.mapper.LoginMapper;
import com.bible.todo.domain.user.vo.UserVo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginService {
	private final LoginMapper loginMapper;
	
	public String LoginProc(LoginDTO loginDTO) {
		UserVo userVo = new UserVo();
		userVo.setUser_id(loginDTO.getId());
		userVo.setUser_pw(loginDTO.getPw());
		String getId = loginMapper.findByUserId(userVo);
		loginDTO.setId(getId);
		return loginDTO.getId();
	}
}
