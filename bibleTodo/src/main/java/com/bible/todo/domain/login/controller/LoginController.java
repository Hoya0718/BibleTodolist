package com.bible.todo.domain.login.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.bible.todo.domain.login.service.LoginService;

@Controller
public class LoginController {
	private final LoginService loginService;
	
	@Autowired
	public LoginController(LoginService loginService) {
		this.loginService = loginService;
	}
	@GetMapping("/login")
	public String loginP() {
		return "login.html";
	}
}
