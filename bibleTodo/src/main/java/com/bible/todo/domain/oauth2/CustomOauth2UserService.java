package com.bible.todo.domain.oauth2;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class CustomOauth2UserService extends DefaultOAuth2UserService{
	
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException{
		
		OAuth2User oAuth2User = super.loadUser(userRequest);
		System.out.println(oAuth2User);
		
		String registrationId = userRequest.getClientRegistration().getRegistrationId();
		OAuth2Response oAuth2Response = null;
		
		if(registrationId.equals("naver")){
			oAuth2Response = new NaverResponse(oAuth2User.getAttributes());
		}
		
		else if ( registrationId.equals("google")){		
			oAuth2Response = new GoogleResponse(oAuth2User.getAttributes());
		}
		
		else if (registrationId.equals("kakao")) {
			oAuth2Response = new GoogleResponse(oAuth2User.getAttributes());
		}
		
		else {
			return null;
		}
		
		String role = "ROLE_ADMIN";
		
		return new CustomOAuth2User(oAuth2Response, role);
	}
}