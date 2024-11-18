package com.bible.todo.domain.bible.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bible.todo.domain.bible.dto.BibleDTO;
import com.bible.todo.domain.bible.service.BibleService;

import lombok.RequiredArgsConstructor;


@RestController // JSON 형식으로 응답을 반환
@RequestMapping("/api")
@RequiredArgsConstructor
public class BibleController {
    private final BibleService bibleService;


    // 성경 구절을 반환하는 API 엔드포인트
    @GetMapping("/{bible_id}")
    public ResponseEntity<String> getBible(@PathVariable("bible_id") int bible_id) {
        String bibleContent = bibleService.getBible(bible_id);
        
        if (bibleContent != null) {
            return ResponseEntity.ok(bibleContent); // 200 OK 응답
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found 응답
        }
    }

    // 정적 HTML 파일 반환
    @GetMapping("/")
    public String index(Model model) {
        return "index"; // index.html 파일 반환
    }
    
    @PostMapping("/getBibleTestament")
    public ResponseEntity<List<String>> getBibleTestament(@RequestBody BibleDTO bibleDTO) {
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
    	System.out.println(bibleDTO);
    	Map<String, Object> map = new HashMap<>();
    	map.put("list", bibleDTO.getList());
    	map.put("chapter", bibleDTO.getChapter());
    	map = bibleService.getContent(bibleDTO);
    	System.out.println(map);
    	return ResponseEntity.ok(map);
    }
    
    @PostMapping("/getSelectedContent")
    public ResponseEntity<List<Map<String,Object>>> getSelectedContent(@RequestBody BibleDTO bibleDTO) {
    	List<Map<String, Object>> result = bibleService.getSelectedContent(bibleDTO);
    	return ResponseEntity.ok(result);
    }

}
