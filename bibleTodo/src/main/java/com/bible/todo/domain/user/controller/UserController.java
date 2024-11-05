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

    @PostMapping("/home")
    public String signUp(@ModelAttribute UserVo userVo) {
        // 회원가입 처리
        userService.signUp(userVo);
        // 회원가입 후 index로 리다이렉트
        return "redirect:/home";  // 리다이렉트 처리
    }
   
    @GetMapping("/home")
    public String showHomePage() {
        // "home.html"을 static 폴더에서 처리하려면 리다이렉트
        return "redirect:/home.html"; // static 폴더의 home.html로 리다이렉트
    }
}

