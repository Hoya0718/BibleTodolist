package com.bible.todo.domain.join.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class JoinDTO {


	@NotBlank(message = "id 입력은 필수 입니다.")
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
