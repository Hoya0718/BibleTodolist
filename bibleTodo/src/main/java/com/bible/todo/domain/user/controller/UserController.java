package com.bible.todo.domain.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.bible.todo.domain.user.service.UserService;
import com.bible.todo.domain.user.vo.UserVo;


@Controller
public class UserController{
	private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    @GetMapping("/index")
    public String showHomePage() {
        return "forward:/static/index.html";  // 'index.html'을 forward로 처리
    }
}
