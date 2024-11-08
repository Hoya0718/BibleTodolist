package com.bible.todo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.extern.log4j.Log4j2;

@Configuration
@Log4j2
@EnableWebSecurity
public class CustomSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        
        log.info("---------- Security Configuration Started ----------");

        // 모든 요청을 허용 (인증 없이 접근 가능)
        http
    	.csrf((auth) ->
    		auth
    			.ignoringRequestMatchers("/joinProc")
    			.disable()
    	);
        
        http
        	.authorizeHttpRequests((auth) ->
        		auth
        			.requestMatchers("/login", "/join", "/login.html", "/join.html", "/joinProc").permitAll()
        			.requestMatchers("/admin.html").hasRole("ADMIN")
        			.requestMatchers("/static/**", "/", "index", "index.html").hasAnyRole("ADMIN", "USER")
        			.anyRequest().authenticated()
        		);
        
        http
        	.formLogin((auth) -> //로그인이 필요할 떄 로그인 사이트로 이동 가능하게
        		auth
        			.loginPage("/login") //사용자 정의 로그인 페이지
        			.loginProcessingUrl("/loginProc") //로그인 프로세싱 URL 로그인 페이지의 action
        			.permitAll()
        		);

        log.info("---------- Security Configuration Complete ----------");

        return http.build();
    }

    @Bean //어느곳이든 호출 가능
    public BCryptPasswordEncoder bCryptPasswordEncoder() { //메서드 호출 시 BCryptPasswordEncoder() 생성
        // 비밀번호 암호화 방식 설정
        return new BCryptPasswordEncoder();
    }
}
