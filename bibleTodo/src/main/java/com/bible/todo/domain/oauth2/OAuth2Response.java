package com.bible.todo.domain.oauth2;

public interface OAuth2Response {
	
	//제공자 (ex. naver, google, kakao, ...)
	String getProvider();
	//제공자에서 발급해주는 아이디(번호)
	String getProviderId();
	//사용자 이메일 /우리 프로젝트는 사용자 아이디를 넣을 것이다.
	String getEmail();
	//사용자 실명(설정한 이름) / 우리프로젝트는 oAuth2의 프로바이더와 프로바이더 아이디를 넣을 것이다.
	String getName();
	
}
