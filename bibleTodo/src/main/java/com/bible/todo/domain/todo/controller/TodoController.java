package com.bible.todo.domain.todo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bible.todo.domain.todo.dto.TodoDTO;
import com.bible.todo.domain.todo.service.TodoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class TodoController {
	private final TodoService todoService;
	
	@PostMapping("saveTodo")
	public void saveTodo(@RequestBody TodoDTO todoDTO) {
		todoService.saveTodo(todoDTO);
	}
	
	@GetMapping("getTodo")
	public ResponseEntity<List<Map<String, Object>>>getTodo(){
		List<Map<String, Object>> list = todoService.getTodo();
		return ResponseEntity.ok(list);
	}
	
	@PatchMapping("updateStatus")
	public void updateStatus(@RequestBody TodoDTO todoDTO) {
		System.out.println(todoDTO);
		todoService.updateStatus(todoDTO);
	}
}
