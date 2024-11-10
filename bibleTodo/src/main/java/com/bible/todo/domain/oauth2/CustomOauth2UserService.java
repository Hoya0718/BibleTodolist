package com.bible.todo.domain.oauth2;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.bible.todo.domain.user.mapper.UserMapper;
import com.bible.todo.domain.user.vo.UserVo;

@Service
public class CustomOauth2UserService extends DefaultOAuth2UserService{ 
	
	private final UserMapper userMapper;
	
	public CustomOauth2UserService(UserMapper userMapper) {
		this.userMapper = userMapper;
	}
	
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException{
		
		
		OAuth2User oAuth2User = super.loadUser(userRequest);
		System.out.println(oAuth2User);
		
		String registrationId = userRequest.getClientRegistration().getRegistrationId();
		OAuth2Response oAuth2Response = null;
		
		if(registrationId.equals("naver")){
			System.out.println("이걸 참고해"+registrationId);
			oAuth2Response = new NaverResponse(oAuth2User.getAttributes());
		}
		
		else if (registrationId.equals("google")){	
			System.out.println("이걸 참고해"+registrationId);
			oAuth2Response = new GoogleResponse(oAuth2User.getAttributes());
		}
		
		else if (registrationId.equals("kakao")){
			System.out.println("이걸 참고해"+registrationId);
			oAuth2Response = new KakaoResponse(oAuth2User.getAttributes());
		}
		
		else {
			return null;
		}
		
		
		//구현
		String username = oAuth2Response.getProvider() +" "+oAuth2Response.getProviderId();
		
		UserVo existData = userMapper.findByUsername(username);
		
		String role = null;
		
		if(existData == null) {
			UserVo userVo = new UserVo();
			userVo.setUser_name(username);
			userVo.setUser_id(oAuth2Response.getEmail());
			userVo.setUser_role("ROLE_USER");
			
			userMapper.save(userVo);
		}
		
		
		return new CustomOAuth2User(oAuth2Response, role);
	}
}