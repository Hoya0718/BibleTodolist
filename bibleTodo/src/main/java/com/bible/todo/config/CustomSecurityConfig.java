package com.bible.todo.config;

import java.io.IOException;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
        	.authorizeHttpRequests((auth) ->
        	auth
        		.requestMatchers("/", "/login").permitAll()
        		.requestMatchers("/admin").hasRole("ADMIN")
        		.requestMatchers("my/**").hasAnyRole("ADMIN", "USER")
        		.anyRequest().authenticated()
        		)
        	.formLogin((formLogin) ->
        		formLogin
        			.loginPage("/index") //사용자 정의 로그인 페이지
        			.defaultSuccessUrl("/home") //로그인 성공 후 이동 페이지
        			.failureUrl("/a")
        			.loginProcessingUrl("index")
        			.successHandler(
        					new AuthenticationSuccessHandler() {
        						   @Override
        						    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        						      System.out.println("authentication: " + authentication.getName());
        						      //로그인에 성공한 유저의 이름
        						      response.sendRedirect("/");
        						    }
        						  })
        			
        			)
        	.csrf(AbstractHttpConfigurer::disable)
        	.authorizeHttpRequests((auth) ->
        		auth
        			.requestMatchers("/static/**", "/", "/index", "/home", "/a", "/home.html", "/index.html", "/a.html", "/signIn").permitAll()
        			);// 정적 리소스에 대한 접근 허용

        log.info("---------- Security Configuration Complete ----------");

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // 비밀번호 암호화 방식 설정
        return new BCryptPasswordEncoder();
    }
}
