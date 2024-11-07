package com.bible.todo.domain.user.vo;

import lombok.Data;

@Data
public class UserVo {
	private String user_id;
	private String user_pw;
	private String user_name;
	private String user_department;
	private String user_gender;
	private Boolean enabled;
}
