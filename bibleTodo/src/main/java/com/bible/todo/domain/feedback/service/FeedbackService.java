package com.bible.todo.domain.feedback.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.bible.todo.domain.feedback.dto.FeedbackDTO;
import com.bible.todo.domain.feedback.mapper.FeedbackMapper;
import com.bible.todo.domain.feedback.vo.FeedbackVo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FeedbackService{
	private final FeedbackMapper feedbackMapper;
	
	public List<Map<String, Object>> getSuggestionList (){
		FeedbackVo feedbackVo = new FeedbackVo();
		return feedbackMapper.getSuggestionList();
		
	}
	
	public void postSuggest(FeedbackDTO feedbackDTO) {
		FeedbackVo feedbackVo = new FeedbackVo();
		feedbackVo.setUser_id(feedbackDTO.getUser_id());
		feedbackVo.setTitle(feedbackDTO.getTitle());
		feedbackVo.setSuggest(feedbackDTO.getSuggest());
		
		feedbackMapper.postSuggest(feedbackVo);
	}
	
	public List<Map<String, Object>> getMyFeedback(FeedbackDTO feedbackDTO) {
		FeedbackVo feedbackVo = new FeedbackVo();
		feedbackVo.setUser_id(feedbackDTO.getUser_id());
		
		return feedbackMapper.getMyFeedback(feedbackVo);
	}
}
