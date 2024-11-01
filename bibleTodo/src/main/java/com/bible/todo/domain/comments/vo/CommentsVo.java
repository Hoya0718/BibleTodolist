package com.bible.todo.domain.comments.vo;

import java.util.Date;

import lombok.Data;

@Data
public class CommentsVo {
	private int no;
	private String comment;
	private int like_count;
	private Date creation_date;
	private String user_id;
	private int bible_id;
}
