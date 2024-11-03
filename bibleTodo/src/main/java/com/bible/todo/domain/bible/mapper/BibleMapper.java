package com.bible.todo.domain.bible.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BibleMapper {
	
	//성경 출력 예제
	//@Param은 SQL 쿼리에서 사용할 때 bible_id라는 이름으로 참조할 수 있도록 한다.
	String getBible(@Param("bible_id") int bible_id);
}
