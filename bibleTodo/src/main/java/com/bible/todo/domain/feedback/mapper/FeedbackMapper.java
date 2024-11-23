package com.bible.todo.domain.feedback.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.bible.todo.domain.feedback.vo.FeedbackVo;

@Mapper
public interface FeedbackMapper {
	List<Map<String, Object>> getSuggestionList();
	
	void postSuggest(FeedbackVo feedbackVo);
}

