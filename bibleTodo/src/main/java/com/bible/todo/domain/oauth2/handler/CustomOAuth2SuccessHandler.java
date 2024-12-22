package com.bible.todo.domain.oauth2.handler;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomOAuth2SuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();

        // 로그인 성공한 사용자 정보 (예: 사용자 이름, 이메일, 아이디 등)
        String userName = oAuth2User.getName();
        String userId = (String) oAuth2User.getAttributes().get("email");  // 예시로 'sub' 속성을 사용
        
        // 로그인 후, URL에 사용자 정보를 포함하여 리디렉션
        String redirectUrl = "http://localhost:3000/OAuth2Callback?userId=" + userId;
        response.sendRedirect(redirectUrl);
    }
}

