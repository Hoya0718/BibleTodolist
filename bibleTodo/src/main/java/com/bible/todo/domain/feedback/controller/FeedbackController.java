package com.bible.todo.domain.feedback.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bible.todo.domain.feedback.dto.FeedbackDTO;
import com.bible.todo.domain.feedback.service.FeedbackService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class FeedbackController {
	private final FeedbackService feedbackService;
	
	@GetMapping("/getSuggestionList")
	public ResponseEntity<List<Map<String, Object>>> getSuggestionList (){
		System.out.println("실행");
		List<Map<String, Object>> list = feedbackService.getSuggestionList();
		System.out.println("값" + list);
		return ResponseEntity.ok(list);
	}
	
	@PostMapping("/postSuggest")
	public void postSuggest(@RequestBody FeedbackDTO feedbackDTO) {
		feedbackService.postSuggest(feedbackDTO);
	}
	
	@PostMapping("/getMyFeedback")
	public ResponseEntity<List<Map<String, Object>>> getMyFeedback(@RequestBody FeedbackDTO feedbackDTO) {
		List<Map<String, Object>> list = feedbackService.getMyFeedback(feedbackDTO);
		System.out.println("실행"+ list);
		return ResponseEntity.ok(list);
	}
}
