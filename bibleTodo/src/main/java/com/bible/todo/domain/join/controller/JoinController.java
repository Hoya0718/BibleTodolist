package com.bible.todo.domain.join.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.bible.todo.domain.join.service.JoinService;
import com.bible.todo.domain.user.vo.UserVo;


@Controller
public class JoinController {
	private final JoinService joinService;
	
	@Autowired
	public JoinController(JoinService joinService) {
		this.joinService = joinService;
	}
	
	@GetMapping("/join")
	public String joinP() {
		return "join.html";
	}
	
	@PostMapping("/joinProc")
	public String joinProcess(UserVo userVo) {
		joinService.joinProcess(userVo);
		return "redirect:/login.html";
	}
	
}
