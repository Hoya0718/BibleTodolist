package com.bible.todo.domain.bible.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bible.todo.domain.bible.dto.BibleDTO;
import com.bible.todo.domain.bible.dto.BibleLikeDTO;
import com.bible.todo.domain.bible.service.BibleService;
import com.bible.todo.domain.checkbible.dto.CheckBibleDTO;
import com.bible.todo.domain.checkbible.service.CheckBibleService;

import lombok.RequiredArgsConstructor;

@RestController // JSON 형식으로 응답을 반환
@RequestMapping("/api")
@RequiredArgsConstructor
public class BibleController {
	private final BibleService bibleService;
	private final CheckBibleService checkBibleService;
	
	@GetMapping("/getTestament")
	public ResponseEntity<List<String>> getTestament() {
		List<String> list = bibleService.getTestament();
		return ResponseEntity.ok(list);
	}

	@PostMapping("/getBibleTestament")
	public ResponseEntity<List<String>> getBibleTestament(@RequestBody BibleDTO bibleDTO) {
		System.out.println("실행했다" +  bibleDTO);
		List<String> list = new ArrayList<>();
		list = bibleService.getTestamentList(bibleDTO.getTestament());
		return ResponseEntity.ok(list);
	}

	@PostMapping("/getChapter")
	public ResponseEntity<List<String>> getChapter(@RequestBody BibleDTO bibleDTO) {
		List<String> chapter = new ArrayList<>();
		chapter = bibleService.getChapter(bibleDTO.getList());
		return ResponseEntity.ok(chapter);
	}

	@PostMapping("/getVerse")
	public ResponseEntity<List<String>> getVerse(@RequestBody BibleDTO bibleDTO) {
		List<String> verse = new ArrayList<>();
		verse = bibleService.getVerse(bibleDTO);
		return ResponseEntity.ok(verse);
	}

	@PostMapping("/getContent")
	public ResponseEntity<Map<String, Object>> getContent(@RequestBody BibleDTO bibleDTO) {
		Map<String, Object> map = new HashMap<>();
		map.put("list", bibleDTO.getList());
		map.put("chapter", bibleDTO.getChapter());
		map = bibleService.getContent(bibleDTO);
		return ResponseEntity.ok(map);
	}

	@PostMapping("/getSelectedContent")
	public ResponseEntity<Map<String, Object>> getSelectedContent(@RequestBody BibleDTO bibleDTO) {
		
		// getSelectedContent가 null이나 빈 리스트를 반환할 수 있는지 체크
		Map<String, Object> result = bibleService.getSelectedContent(bibleDTO);
		// 리스트가 비어있거나 null일 경우 처리
		if (result == null || result.isEmpty()) {
			System.out.println("getSelectedContent에서 반환된 데이터가 비어있습니다.");
		} else {
			System.out.println("getSelectedContent에서 반환된 데이터: " + result);
		}

		// maxChapter 가져오기
		Integer maxChapter = bibleService.getMaxChapter(bibleDTO.getList());

		result.put("maxChapter", maxChapter);
		// 최종 결과 출력
		System.out.println("컨텐츠 여기" + result);
		
		// 결과 반환
		return ResponseEntity.ok(result);
	}

	@PostMapping("/getPrevChapterMaxVerse")
	public ResponseEntity<String> getPrevChapterMaxVerse() {

		return ResponseEntity.ok(null);

	}

	@PostMapping("/prevList")
	public ResponseEntity<Map<String, Object>> prevList(@RequestBody BibleDTO bibleDTO) {
		Map<String, Object> map = bibleService.prevList(bibleDTO.getList());
		return ResponseEntity.ok(map);
	}

	@PostMapping("/nextList")
	public ResponseEntity<Map<String, Object>> nextList(@RequestBody BibleDTO bibleDTO) {
		System.out.println("다음 목차");
		Map<String, Object> map = bibleService.nextList(bibleDTO.getList());
		return ResponseEntity.ok(map);
	}

	@PostMapping("/getMaxVerse")
	public ResponseEntity<Map<String, Object>> getMaxVerse(@RequestBody BibleDTO bibleDTO) {
		Map<String, Object> map = bibleService.getMaxVerse(bibleDTO);
		return ResponseEntity.ok(map);
	}
	
	@PostMapping("/SearchWord")
	public ResponseEntity<List<Map<String, Object>>> SearchWord(@RequestBody BibleDTO bibleDTO) {
		List<Map<String, Object>> list = bibleService.SearchWord(bibleDTO);
		System.out.println("실행" + list);
		return ResponseEntity.ok(list);
	}
	
	@PostMapping("/likeBible")
	public void likeBible(@RequestBody BibleLikeDTO bibleLikeDTO) {
		bibleService.likeBible(bibleLikeDTO);
	}
	
	@PostMapping("/getMyLoveBible")
	public ResponseEntity<List<Map<String, Object>>> getMyLoveBible(@RequestBody BibleLikeDTO bibleLikeDTO){
		List<Map<String, Object>> list = bibleService.getMyLoveBible(bibleLikeDTO);
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("/todayBible")
	public ResponseEntity<Map<String, Object>> todayBible() {
		Map<String, Object> map = bibleService.todayBible();
		System.out.println(map);
		return ResponseEntity.ok(map);
	}
}
