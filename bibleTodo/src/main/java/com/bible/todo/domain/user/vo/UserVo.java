package com.bible.todo.domain.user.vo;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class UserVo {
	private String user_id;
	private String user_pw;
	private String user_name;
	private String user_department;
	private String user_gender;
	private String user_role;
}
