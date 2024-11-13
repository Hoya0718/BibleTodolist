package com.bible.todo.domain.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.bible.todo.domain.login.dto.LoginDTO;
import com.bible.todo.domain.login.service.LoginService;


import jakarta.servlet.http.HttpSession;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LoginController {
	private final LoginService loginService;
	
	@GetMapping("/login")
	public String loginP() {
		return "login.html";
	}
	
	@PostMapping("/loginProc")
	public String loginProc(HttpSession session, LoginDTO loginDTO) {
		String getId = loginService.LoginProc(loginDTO);
		
		if(getId == null) {
			System.out.println("아이디가/비밀번호가 틀리거나 존재하지 않습니다.");
			return "redirect:/login";
		}
		
		session.setAttribute("userId", getId);
		return "admin";
	}
	
	@PostMapping("logoutProc")
	public String logoutProc(HttpSession session) {
			System.out.println("로그아웃 전 세션" + session.getAttribute("userId"));
			session.invalidate();

			return "login";
	}
	
	@PostMapping("isSession")
	public String isSession(HttpSession session) {
		String userId = (String) session.getAttribute("userId");
		if(userId != null) {
			System.out.println(userId);
			return "redirect:/login";
		}
		else {
			System.out.println("세션이 없어요");
			return "redirect:/login";
		}
	}
}
