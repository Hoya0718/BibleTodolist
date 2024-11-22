package com.bible.todo.domain.todo.vo;

import lombok.Data;

@Data
public class TodoVo {
	
	private int id;
	
	private String title;
	
	private String list;
	
	private int firstChapter;
	
	private int lastChapter;
	
	private String status;
	
	private String user_id;
}
