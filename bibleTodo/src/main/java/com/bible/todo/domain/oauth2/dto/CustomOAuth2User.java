package com.bible.todo.domain.oauth2.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class CustomOAuth2User implements OAuth2User {
    
    private final OAuth2Response oAuth2Response;
    private final String role;

    public CustomOAuth2User(OAuth2Response oAuth2Response, String role) {
    	if(oAuth2Response == null) {
    		throw new IllegalArgumentException("OAuth2Response must not be null");
    	}
    	
        this.oAuth2Response = oAuth2Response;
        this.role = role;
    }

    @Override
    public Map<String, Object> getAttributes() {
        // oAuth2Response가 정상적으로 설정되었으므로, 이를 반환합니다.
        return oAuth2Response != null ? oAuth2Response.getAttributes() : null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add(new GrantedAuthority() {
            
            @Override
            public String getAuthority() {
                return role;
            }
        });
        return collection;
    }

    @Override
    public String getName() {
    	System.out.println("1번" + oAuth2Response.getName());
        return oAuth2Response.getName();
    }

    public String getUserName() {
    	System.out.println("2번" + oAuth2Response.getProvider() + " " + oAuth2Response.getProviderId());
        return oAuth2Response.getProvider() + " " + oAuth2Response.getProviderId();
    }
    

    // getOAuth2Response 메서드 추가
    public OAuth2Response getOAuth2Response() {
    	System.out.println("3번" + oAuth2Response);
        return oAuth2Response;
    }
}
