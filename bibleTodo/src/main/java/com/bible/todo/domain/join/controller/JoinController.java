package com.bible.todo.domain.join.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.bible.todo.domain.join.dto.JoinDTO;
import com.bible.todo.domain.join.service.JoinService;
import com.bible.todo.domain.user.vo.UserVo;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class JoinController {
	private final JoinService joinService;
	
	
	@GetMapping("/join")
	public String joinP() {
		return "join";
		
	}
	
	@PostMapping("/joinProc")
	public String joinProcess(@Valid @ModelAttribute JoinDTO joinDTO,BindingResult result) {
		
	
		

		
		System.out.println(joinDTO);
		if(result.hasErrors()) {
			return "redirect:/join";
		}
				
		joinService.joinProcess(joinDTO);
		return "redirect:/login";
	}
	
}
