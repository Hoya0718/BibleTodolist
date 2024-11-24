package com.bible.todo.domain.comments.dto;

import lombok.Data;

@Data 
public class CommentDTO {
	private int comment_id;
	private  String user_id;
	private  String comment;
	private  String list;
	private  int chapter;
	private  int verse;
	private  int like_count;
}
