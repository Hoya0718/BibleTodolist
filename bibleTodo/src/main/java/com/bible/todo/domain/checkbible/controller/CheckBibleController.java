package com.bible.todo.domain.checkbible.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
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
	
	@PostMapping("/getLastReading")
	public ResponseEntity<Map<String,Object>> getLastReading(@RequestBody CheckBibleDTO checkBibleDTO){
		System.out.println(checkBibleDTO);
		Map<String, Object> map = checkBibleService.getLastReading(checkBibleDTO);
		System.out.println("반환값" + map);
		if(map == null) {
			map = new HashMap<>();
		
			map.put("list", "창세기");
			map.put("chapter", 1);
			map.put("verse", 1);
			return ResponseEntity.ok(map);
		}
		return ResponseEntity.ok(map);
	}
}
