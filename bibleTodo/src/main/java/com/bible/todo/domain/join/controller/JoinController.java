package com.bible.todo.domain.join.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bible.todo.domain.join.dto.JoinDTO;
import com.bible.todo.domain.join.service.JoinService;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class JoinController {
	private final JoinService joinService;
	
	@PostMapping("/joinProc")
	public ResponseEntity<Map<String, String>> joinProcess(@RequestBody JoinDTO joinDTO,BindingResult result) {
	
		Map<String, String> response = new HashMap<>();

		if(result.hasErrors()) {
			System.err.println("회원가입 실패했습니다.");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);  // 회워가입 실패 응답
		}
		joinService.joinProcess(joinDTO);
		response.put("message","success");
		return ResponseEntity.ok(response);
	}
	
}
