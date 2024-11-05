package com.bible.todo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Configuration
public class BibleSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // 모든 요청에 대해 인증 없이 접근을 허용
        http
            .authorizeHttpRequests()
                .anyRequest().permitAll()  // 모든 경로에 대해 접근 허용
            .and()
            .csrf().disable();  // CSRF 보호 비활성화 (API 서버나 특정 상황에서 필요할 수 있음)

        log.info("--------------------Security Configuration Complete--------------------");

        return http.build();
    }
}
