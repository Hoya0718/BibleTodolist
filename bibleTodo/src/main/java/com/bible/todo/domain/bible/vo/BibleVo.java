package com.bible.todo.domain.bible.vo;

import lombok.Data;

@Data
public class BibleVo {
	private int bible_id; //성경 id
	private String testament; //구약 or 신약
	private String list; // 성경 목차
	private int chapter; //성경 장
	private int verse; //성경 절
	private String content; //성경 내용
}
