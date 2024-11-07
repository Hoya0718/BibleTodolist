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

    @PostMapping("/index")
    public String signUp(@ModelAttribute UserVo userVo) {
        userService.signUp(userVo);
        return "redirect:/index";  // 회원가입 후 인덱스 페이지로 리다이렉트
    }
    
    @GetMapping("/index")
    public String showHomePage() {
        return "forward:/static/index.html";  // 'index.html'을 forward로 처리
    }

    //로그인 프로세스 동작 -> 시큐리티가 처리하기 때문에 구게적인 로직 구현X
    @PostMapping("/")
    public  ResponseEntity<?> authenticateUser() {
       return ResponseEntity.ok().build();
    }

    // 로그인 성공 후 /home
    @GetMapping("/home")
    public String getHome() {
        return "forward:/static/home.html";  // 'home.html'을 forward로 처리
    }
}
