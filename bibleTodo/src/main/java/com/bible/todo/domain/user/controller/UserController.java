package com.bible.todo.domain.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.bible.todo.domain.user.service.UserService;
import com.bible.todo.domain.user.vo.UserVo;

@Controller
public class UserController {

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

    @PostMapping("/signIn")
    public String sign(@ModelAttribute UserVo userVo) {
        userService.signIn(userVo);
        return "redirect:/home";  // 로그인 후 /home으로 리디렉트
    }

    // 로그인 실패 후 /a로 리디렉션
    @GetMapping("/a")
    public String errorPage() {
        return "forward:/static/a.html";  // 'a.html'을 forward로 처리
    }

    // 로그인 성공 후 /home
    @GetMapping("/home")
    public String getHome() {
        return "forward:/static/home.html";  // 'home.html'을 forward로 처리
    }
}
