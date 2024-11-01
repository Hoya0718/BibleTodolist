package com.bible.todo.domain.bible.vo;

import lombok.Data;

@Data
public class BibleVo {
	private int bible_id;
	private String testament;
	private String list;
	private String chapter;
	private String verse;
	private String content;
	
}
