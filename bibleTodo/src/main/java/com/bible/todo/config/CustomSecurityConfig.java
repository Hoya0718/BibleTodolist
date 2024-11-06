package com.bible.todo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.extern.log4j.Log4j2;

@Configuration
@Log4j2
public class CustomSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        
        log.info("---------- Security Configuration Started ----------");

        // 모든 요청을 허용 (인증 없이 접근 가능)
        http.authorizeHttpRequests(auth -> auth
            .anyRequest().permitAll()  // 모든 요청을 허용
        )
        .csrf(csrf -> csrf.disable());  // CSRF 보호 비활성화 (원하는 경우)

        log.info("---------- Security Configuration Complete ----------");

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // 비밀번호 암호화 방식 설정
        return new BCryptPasswordEncoder();
    }
}
