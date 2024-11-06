package com.bible.todo.domain.user.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class CustomerDetailsService implements UserDetailsService{
	
	private PasswordEncoder passwordEncoder;
	
	public CustomerDetailsService() {
		this.passwordEncoder = new BCryptPasswordEncoder();
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException  {
		
		log.info("loadUserByUsername: " + username);
		
		UserDetails userDetails = User.builder().username("user1")
				//.password("1111")
				.password(passwordEncoder.encode("1111"))
				.authorities("ROLE_USER")
				.build();
		
		return userDetails;
	}

}
