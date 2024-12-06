package com.bible.todo.domain.oauth2.dto;

import java.util.Map;


public class NaverResponse implements OAuth2Response{
	
	private final Map<String, Object> attribute;
	
	public NaverResponse(Map<String, Object> attribute) {
		this.attribute = (Map<String, Object>) attribute.get("response");
	}
	
	@Override
	public String getProvider() {
		System.out.println("네이버 정보"+attribute);
		return "naver";
	}
	
	@Override
	public String getProviderId() {
		System.out.println("네이버 아이디" + attribute.get("id"));
		return attribute.get("id").toString();
	}
	
	@Override
	public String getEmail() {
		System.out.println("네이버 이메일 실행" + attribute.get("email"));
		return attribute.get("email").toString();
		
	}
	
	@Override
	public String getName() {
		System.out.println("네이버 이름" + attribute.get("name"));
		return attribute.get("name").toString();
	}
	
	@Override
    public Map<String, Object> getAttributes() {
        return attribute;  // 실제 Kakao 응답 속성 반환
    }

}
