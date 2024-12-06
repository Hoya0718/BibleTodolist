package com.bible.todo.domain.oauth2.dto;

import java.util.Map;

public class KakaoResponse implements OAuth2Response {

    private final Map<String, Object> attribute;

    public KakaoResponse(Map<String, Object> attribute) {
        this.attribute = attribute;
    }

    @Override
    public String getProvider() {
        return "kakao";
    }

    @Override
    public String getProviderId() {
        return attribute.get("id").toString();  // 카카오는 "id"를 직접 사용
    }

    @Override
    public String getEmail() {
    	Map<String, Object> map = (Map<String, Object>) attribute.get("properties");
    	Object email = map.get("nickname");
    
        return email.toString();
    }

    @Override
    public String getName() {
    	Map<String, Object> map = (Map<String, Object>) attribute.get("properties");
    	Object nickname = map.get("nickname");
    
        return nickname.toString();
    }
    
    @Override
    public Map<String, Object> getAttributes() {
        return attribute;  // 실제 Kakao 응답 속성 반환
    }
}
