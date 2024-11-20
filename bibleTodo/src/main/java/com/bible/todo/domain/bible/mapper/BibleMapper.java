package com.bible.todo.domain.bible.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.bible.todo.domain.bible.vo.BibleVo;

@Mapper
public interface BibleMapper {
	
	//성경 출력 예제
	//@Param은 SQL 쿼리에서 사용할 때 bible_id라는 이름으로 참조할 수 있도록 한다.
	String getBible(@Param("bible_id") int bible_id);
	
	List<String> getTestamentList(BibleVo bibleVo);
	
	List<String> getChapter(BibleVo bibleVo);
	
	List<String> getVerse(BibleVo bibleVo);
	
	Map<String, Object> getContent(BibleVo bibleVo);
	
	List<Map<String, Object>> getSelectedContent(BibleVo bibleVo);
	
	Integer getMaxChapter(BibleVo bibleVo);
	
	List<Map<String, Object>> getBibleListMaxChapter();
	
	Map<String, Object> prevList(BibleVo bibleVo);
	
	Map<String, Object> nextList(BibleVo bibleVo);
}
