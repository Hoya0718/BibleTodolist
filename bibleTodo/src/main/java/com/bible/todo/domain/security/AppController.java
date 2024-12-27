package com.bible.todo.domain.security;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AppController implements ErrorController {

    @RequestMapping("/error")
    public String handleError() {
        // 오류 처리 후 index.html로 포워딩
        return "forward:/index.html";
    }
}