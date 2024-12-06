package com.bible.todo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;

import com.bible.todo.domain.oauth2.handler.CustomOAuth2SuccessHandler;
import com.bible.todo.domain.oauth2.service.CustomOAuth2UserService;

@Configuration
public class CustomSecurityConfig {

	private final CustomOAuth2UserService customOAuth2UserService;
	private final CustomOAuth2SuccessHandler customOAuth2SuccessHandler;
	
	public CustomSecurityConfig(CustomOAuth2UserService customOAuth2UserService, CustomOAuth2SuccessHandler customOAuth2SuccessHandler) {
		this.customOAuth2UserService = customOAuth2UserService;
		this.customOAuth2SuccessHandler = customOAuth2SuccessHandler;
	}

	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // CSRF 보호 비활성화 (API 기반 앱일 경우 유용)
        http.csrf(csrf -> csrf.disable());

        // 모든 요청에 대해 인증 없이 접근 허용
        http.authorizeHttpRequests(auth -> auth
            .anyRequest().permitAll()  // 모든 요청에 대해 인증 없이 접근 허용
            
        );

        // OAuth2 로그인 처리
        http.oauth2Login(oauth2 -> oauth2 //OAuth2Login 시작
            .userInfoEndpoint(userInfoEndpointConfig ->  // OAuth2Login 성공 이후 설정을 시작
                userInfoEndpointConfig.userService(customOAuth2UserService))  // customOAuth2UserService에서 처리하겠다.
            .successHandler(customOAuth2SuccessHandler)
           );

        // 세션 관리 설정 (동시 세션 수 제한)
        http.sessionManagement(session -> session
            .maximumSessions(1)  // 동시에 한 개의 세션만 허용
            .maxSessionsPreventsLogin(true)  // 이미 세션이 있을 경우 추가 로그인 방지
        );

        return http.build();  // HttpSecurity 객체를 사용해 SecurityFilterChain 빌드
    }
}
