package com.bible.todo.domain.user.mapper;

import java.util.ArrayList;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.bible.todo.domain.user.vo.UserVo;

@Mapper
public interface UserMapper {
	
	//회원가입
	void signUp(UserVo userVo);
	
	//마이페이지에서 비번 변경 -> 비밀번호
	String checkPw(@Param("user_id") String user_id);
	
	//비밀번호 찾기 -> 아이디를 통해 비밀번호 변경
	String findPw(@Param("user_id") String user_id);
	
	//비번변경 파라미터 -> 바꿀 비밀번호  
	ArrayList<String> editPw(@Param("user_id") String user_id,  @Param("user_pw")String user_pw );

	UserVo signIn(UserVo userVo);

    UserVo findByUsername(String username);
	
}
