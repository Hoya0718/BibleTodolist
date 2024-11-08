package com.bible.todo.domain.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.bible.todo.domain.user.service.UserService;


@Controller
public class UserController{
	private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    

}
