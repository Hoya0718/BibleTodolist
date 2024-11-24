package com.bible.todo.domain.comments.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.bible.todo.domain.comments.dto.CommentDTO;
import com.bible.todo.domain.comments.service.CommentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CommentController {
	private final CommentService commentService;
	
	 @PostMapping("/writeComment")
	    public void writeComment(@RequestBody CommentDTO commentDTO){
	    	commentService.writeComment(commentDTO);
	    }
	 
	 @GetMapping("/getComment")
	 public ResponseEntity<List<Map<String, Object>>> getComment(){
		 List<Map<String, Object>> list = commentService.getComment();
		 return ResponseEntity.ok(list);
	 }
	 
	 @PostMapping("/likeUp")
	 public void likeUp(@RequestBody CommentDTO commentDTO) {
		 commentService.likeUp(commentDTO);
	 }
	 
}
