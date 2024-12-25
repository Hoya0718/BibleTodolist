package com.bible.todo.domain.oauth2.service;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.bible.todo.domain.oauth2.dto.CustomOAuth2User;
import com.bible.todo.domain.oauth2.dto.GoogleResponse;
import com.bible.todo.domain.oauth2.dto.KakaoResponse;
import com.bible.todo.domain.oauth2.dto.NaverResponse;
import com.bible.todo.domain.oauth2.dto.OAuth2Response;
import com.bible.todo.domain.user.mapper.UserMapper;
import com.bible.todo.domain.user.vo.UserVo;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    private final UserMapper userMapper;

    public CustomOAuth2UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    //loadUser() : 네이버나 구글의 사용자 정보 데이터를 인자로 받아온다.
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
    	
        OAuth2User oAuth2User = super.loadUser(userRequest);
        //어떤 인증 프로바이드인지 확인 ex. naver, google
        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        System.out.println("Registration Id: " + registrationId);
        // OAuth2Response 데이터를 받을 바구니
        OAuth2Response oAuth2Response = null;
        if (registrationId.equals("google")) {
            oAuth2Response = new GoogleResponse(oAuth2User.getAttributes());
        } else if (registrationId.equals("naver")) {
            oAuth2Response = new NaverResponse(oAuth2User.getAttributes());
        } else if (registrationId.equals("kakao")) {
            oAuth2Response = new KakaoResponse(oAuth2User.getAttributes());
        }

        // oAuth2Response가 null이면 예외를 발생시켜서 문제를 알 수 있게 합니다.
        if (oAuth2Response == null) {
            throw new OAuth2AuthenticationException("OAuth2 Response is null for registrationId: " + registrationId);
        }

        String username = oAuth2Response.getProvider() + " " + oAuth2Response.getProviderId();
        UserVo existData = userMapper.findByUsername(username);
        
        if (existData == null) {
            UserVo userVo = new UserVo();
            userVo.setUser_name(username);
            userVo.setUser_id(oAuth2Response.getEmail());
            userVo.setUser_role("ROLE_USER");
            userMapper.save(userVo);
        }

        // CustomOAuth2User에 oAuth2Response 전달
        return new CustomOAuth2User(oAuth2Response, "ROLE_USER");
    }
}

