package com.bible.todo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000")  // React 앱 포트
                .allowedMethods("OPTIONS", "GET", "POST", "PUT", "DELETE");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // '/' 경로로 들어오면 index.html을 서빙
        // forward 대신 /index.html 경로를 직접 서빙하도록 설정
    	registry.addViewController("/").setViewName("forward:/index.html");
    	registry.addViewController("/basic").setViewName("forward:/index.html");
    	registry.addViewController("/OAuth2Callback").setViewName("forward:/index.html");
    	registry.addViewController("/error").setViewName("forward:/index.html");
        // 정적 파일은 Spring Boot가 자동으로 서빙함
        // /js, /css 등 추가 포워딩은 불필요
        // Spring Boot가 자동으로 서빙하므로 해당 라인 삭제
        // registry.addViewController("/js/**").setViewName("forward:/js/");
        // registry.addViewController("/css/**").setViewName("forward:/css/");
        // 기타 정적 파일은 Spring Boot가 자동으로 처리함
        // registry.addViewController("/favicon.ico").setViewName("forward:/favicon.ico");
        // registry.addViewController("/manifest.json").setViewName("forward:/manifest.json");
    }
}
