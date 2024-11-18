package com.bible.todo.domain.login.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.bible.todo.domain.login.dto.LoginDTO;
import com.bible.todo.domain.login.mapper.LoginMapper;
import com.bible.todo.domain.user.vo.UserVo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginService {
	private final LoginMapper loginMapper;
	
	public Map<String, Object> LoginProc(LoginDTO loginDTO) {
		UserVo userVo = new UserVo();
		userVo.setUser_id(loginDTO.getUser_id());
		userVo.setUser_pw(loginDTO.getUser_pw());
		return loginMapper.findByUserId(userVo);
	}
}
