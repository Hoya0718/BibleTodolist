package com.bible.todo.domain.join.vo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class JoinVo {
	@NotBlank
	private String id;
	
	@NotBlank
	private String pw;
	
	@NotNull
	private String name;
	
	@NotNull
	private String department;
	
	@NotNull
	private String gender;
}
