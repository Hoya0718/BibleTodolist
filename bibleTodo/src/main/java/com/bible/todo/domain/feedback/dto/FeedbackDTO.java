package com.bible.todo.domain.feedback.dto;

import lombok.Data;

@Data
public class FeedbackDTO {
	private String user_id;
	private String title;
	private String suggestion;
}
