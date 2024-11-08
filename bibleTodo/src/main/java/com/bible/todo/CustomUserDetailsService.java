package com.bible.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bible.todo.domain.CustomUserDetails;
import com.bible.todo.domain.login.mapper.LoginMapper;
import com.bible.todo.domain.user.vo.UserVo;

import lombok.Data;

@Data
@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserVo userVo;
	@Autowired
	private LoginMapper loginMapper;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
			
		UserVo userVo = loginMapper.findByUserId(username);
			
		if(userVo != null) {
			return new CustomUserDetails(userVo);
		}
		return null;
	}
}
