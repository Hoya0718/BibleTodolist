package com.bible.todo.domain.login.controller;

import java.util.HashMap;
import java.util.Map;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bible.todo.domain.login.dto.LoginDTO;
import com.bible.todo.domain.login.service.LoginService;


import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class LoginController {
	private final LoginService loginService;
	
	@GetMapping("/login")
	public String loginP() {
		return "login.html";
	}
	
	@PostMapping("/loginProc")
	public ResponseEntity<Map<String,Object>> loginProc(@RequestBody LoginDTO loginDTO) {
		Map<String, Object> map = loginService.LoginProc(loginDTO);
		return ResponseEntity.ok(map);
	}
	@PostMapping("/oauth2/user")
	public ResponseEntity<Map<String,Object>> oAuth2LoginProc(@RequestBody LoginDTO loginDTO) {
		Map<String, Object> map = new HashMap<>();
		map.put("user_id", loginDTO.getUser_id());
		map.put("user_role", "ROLE_USER");
		return ResponseEntity.ok(map);
	}
//		if(response.get(user_id) == null) {
//			System.out.println("아이디가/비밀번호가 틀리거나 존재하지 않습니다.");
//			response.put("message", "아이디가/비밀번호가 틀리거나 존재하지 않습니다.");
//	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);  // 로그인 실패 응답
//		}
//		session.setAttribute("userId", getId);
//		 response.put("message", "success");
//		 return ResponseEntity.ok(response);  // 로그인 성공 응답
	
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
