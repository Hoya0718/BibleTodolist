package com.bible.todo.domain.checkbible.dto;

import lombok.Data;

@Data
public class CheckBibleDTO {
	private String user_id;
	private String list;
	private String[] lists;
	private int chapter;
	private int verse;
}
