package com.bible.todo.domain.feedback.vo;

import java.util.Date;

import lombok.Data;

@Data
public class FeedbackVo {
	private int id;
	private String user_id;
	private String title;
	private String suggestion;
	private Date feedback_date;
}
