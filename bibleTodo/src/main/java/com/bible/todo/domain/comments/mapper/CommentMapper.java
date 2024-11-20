package com.bible.todo.domain.comments.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.bible.todo.domain.bible.vo.BibleVo;
import com.bible.todo.domain.comments.vo.CommentVo;

@Mapper
public interface CommentMapper {
	
	void writeComment(CommentVo commentVo);
	
	int getBibleId(BibleVo bibleVo);
	
	List<Map<String, Object>> getComment();
}