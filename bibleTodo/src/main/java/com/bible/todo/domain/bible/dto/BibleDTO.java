package com.bible.todo.domain.bible.dto;

import lombok.Data;

@Data
public class BibleDTO {
	private int bible_id;
	private String testament;
	private String list;
	private int chapter;
	private int verse;
	private String content;
}
