package com.bible.todo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.extern.log4j.Log4j2;

@Configuration
@Log4j2
@EnableWebSecurity //시큐리티 활성화
public class CustomSecurityConfig {
	
	//계층 권한
	//@Bean
	//public RoleHierarchy roleHierarchy() {

	//return RoleHierarchyImpl.fromHierarchy("""
	//		ROLE_C > ROLE_B
	//		ROLE_B > ROLE_A
	//		""");
	//}
	//
	//@Bean RoleHierarchy roleHierarchy() {
		
	//	return RoleHierarchyImpl.withDefaultRolePrefix()
	//			.role("C").implies("B")
	//			.role("B").implies("A")
	//			.build();
	//}
	
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        
        log.info("---------- Security Configuration Started ----------");

        http
    	.oauth2Login(Customizer.withDefaults());
        
        http
    	.csrf((auth) ->
    		auth
    			.ignoringRequestMatchers("/joinProc")
    			.disable()
    	);
        
        http
        	.authorizeHttpRequests((auth) ->
        		auth
        			.requestMatchers("/login", "/join", "/login.html", "/join.html", "/joinProc","/oauth2/**").permitAll()
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
        //http
        //	.httpBasic(Customizer.withDefaults()); //팝업 로그인 방식
        http
        	.sessionManagement((auth) -> 
        		auth
        			.maximumSessions(3) //
        			.maxSessionsPreventsLogin(true) //최대 로그인 허용치 초과 시 true-> 새로운 로그인 차단, false -> 기존 세션 하나 삭제
        	
        			);
        http
        	.sessionManagement((auth) ->
        		auth
        			.sessionFixation()
        				//.none() //로그인 시 세션 정보 변경 안함
        				//.newSession() //로그인 시 세션 새로 생성
        				.changeSessionId() // 로그인 시 동일한 세션에 대한 id 변경
        			);
        
        http
        	.logout((auth) ->
        		auth
        			.logoutUrl("/logout")
        			.logoutSuccessUrl("/")
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
