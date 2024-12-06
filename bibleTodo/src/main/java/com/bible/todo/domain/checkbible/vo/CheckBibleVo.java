package com.bible.todo.domain.checkbible.vo;

import java.util.Date;

import lombok.Data;

@Data
public class CheckBibleVo {
	private int no;
	private String user_id;
	private int bible_id;
	private boolean isCheck;
	private Date lastdate;
	private String list;
}
