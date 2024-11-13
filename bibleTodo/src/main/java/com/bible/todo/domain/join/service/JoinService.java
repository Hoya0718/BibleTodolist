package com.bible.todo.domain.join.service;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bible.todo.domain.join.dto.JoinDTO;
import com.bible.todo.domain.join.mapper.JoinMapper;
import com.bible.todo.domain.user.vo.UserVo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JoinService {
	private final JoinMapper joinMapper;
	
	
	
	//public BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public void joinProcess(JoinDTO joinDTO) {
		//String encodedPassword  = bCryptPasswordEncoder.encode(joinDTO.getPw());
		UserVo userVo = new UserVo();
		userVo.setUser_id(joinDTO.getId());
		userVo.setUser_pw(joinDTO.getPw());
		userVo.setUser_name(joinDTO.getName());
		userVo.setUser_department(joinDTO.getDepartment());
		userVo.setUser_gender(joinDTO.getGender());
		userVo.setUser_role("ROLE_USER");
		
		//oinDTO.setPw(encodedPassword);
		joinMapper.joinProcess(userVo);
	}
}
