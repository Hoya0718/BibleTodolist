package com.bible.todo.domain.comments.vo;

import lombok.Data;

@Data
public class CommentLikeVo {
	private int commentlike_id;
	private int comment_id;
	private String user_id;
}
