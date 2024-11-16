package com.bible.todo.domain.bible.dto;

import lombok.Data;

@Data
public class BibleDTO {
	private String bible_id;
	private String testament;
	private String chapter;
	private String list;
}
