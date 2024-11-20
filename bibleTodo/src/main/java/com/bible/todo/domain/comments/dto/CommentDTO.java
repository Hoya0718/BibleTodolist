package com.bible.todo.domain.comments.dto;

import lombok.Data;

@Data 
public class CommentDTO {
	private  String user_id;
	private  String comment;
	private  String list;
	private  int chapter;
	private  int verse;
}
