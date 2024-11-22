package com.bible.todo.domain.todo.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.bible.todo.domain.todo.vo.TodoVo;

@Mapper
public interface TodoMapper {
	void saveTodo(TodoVo todoVo);
	
	List<Map<String, Object>> getTodo();
	
	void updateStatus(TodoVo todoVo);
}
