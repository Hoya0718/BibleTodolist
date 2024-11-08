package com.bible.todo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 정적 리소스 폴더에서 HTML 파일 제공
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");

        // 특정 경로로 요청 시 해당 HTML 파일을 제공
        registry.addResourceHandler("/")
                .addResourceLocations("classpath:/static/index.html");

        registry.addResourceHandler("/index")
                .addResourceLocations("classpath:/static/index.html");

        registry.addResourceHandler("/admin")
                .addResourceLocations("classpath:/static/admin.html");
        
        registry.addResourceHandler("/join")
        		.addResourceLocations("classpath:/static/join.html");
    }
}
