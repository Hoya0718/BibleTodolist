package com.bible.todo.domain.todo.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.bible.todo.domain.todo.dto.TodoDTO;
import com.bible.todo.domain.todo.mapper.TodoMapper;
import com.bible.todo.domain.todo.vo.TodoVo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TodoService {
	private final TodoMapper todoMapper;
	
	public void saveTodo(TodoDTO todoDTO) {
		TodoVo todoVo = new TodoVo();
		todoVo.setTitle(todoDTO.getTitle());
		todoVo.setList(todoDTO.getList());
		todoVo.setFirstChapter(todoDTO.getFirstChapter());
		todoVo.setLastChapter(todoDTO.getLastChapter());
		todoVo.setUser_id(todoDTO.getUser_id());
		todoMapper.saveTodo(todoVo);
	}
	
	public List<Map<String, Object>> getTodo() {
		return todoMapper.getTodo();
	}
	
	public void updateStatus(TodoDTO todoDTO) {
		TodoVo todoVo = new TodoVo();
		todoVo.setId(todoDTO.getId());
		todoVo.setStatus(todoDTO.getStatus());
		todoMapper.updateStatus(todoVo);
	}
}
