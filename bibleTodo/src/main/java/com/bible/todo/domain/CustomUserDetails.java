package com.bible.todo.domain;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.bible.todo.domain.user.vo.UserVo;

public class CustomUserDetails implements UserDetails{
	private UserVo userVo;
	
	public CustomUserDetails(UserVo userVo) {
		this.userVo = userVo;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collection = new ArrayList<>();
		collection.add(new GrantedAuthority() {
			@Override
			public String getAuthority() {
				return userVo.getUser_role();			}
		});
		
		return collection;
	}

	@Override
	public String getPassword() {
		return userVo.getUser_pw();
	}

	@Override
	public String getUsername() {
		return userVo.getUser_id();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
}
