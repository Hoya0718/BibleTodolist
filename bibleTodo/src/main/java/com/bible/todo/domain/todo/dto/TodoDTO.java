package com.bible.todo.domain.todo.dto;

import lombok.Data;

@Data
public class TodoDTO {
	private int id;
	private String title;
	private String list;
	private int firstChapter;
	private int lastChapter;
	private String Status;
	private String user_id;
}
