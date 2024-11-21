package com.bible.todo.domain.checkbible.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bible.todo.domain.checkbible.dto.CheckBibleDTO;
import com.bible.todo.domain.checkbible.service.CheckBibleService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CheckBibleController {
	private final CheckBibleService checkBibleService;
	
	@PostMapping("/checkVerse")
	public void checkVerse(@RequestBody CheckBibleDTO checkBibleDTO) {
		checkBibleService.checkVerse(checkBibleDTO);
	}
}
