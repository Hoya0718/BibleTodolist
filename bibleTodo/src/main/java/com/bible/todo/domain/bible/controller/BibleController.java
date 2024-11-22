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
import org.springframework.web.bind.annotation.RestController;

import com.bible.todo.domain.bible.dto.BibleDTO;
import com.bible.todo.domain.bible.service.BibleService;

import lombok.RequiredArgsConstructor;


@RestController // JSON 형식으로 응답을 반환
@RequestMapping("/api")
@RequiredArgsConstructor
public class BibleController {
    private final BibleService bibleService;


    @GetMapping("/getTestament")
    public ResponseEntity<List<String>> getTestament(){
    	List<String> list = bibleService.getTestament();
    	return ResponseEntity.ok(list);
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
    	Map<String, Object> map = new HashMap<>();
    	map.put("list", bibleDTO.getList());
    	map.put("chapter", bibleDTO.getChapter());
    	map = bibleService.getContent(bibleDTO);
    	return ResponseEntity.ok(map);
    }
    
    @PostMapping("/getSelectedContent")
    public ResponseEntity<List<Map<String, Object>>> getSelectedContent(@RequestBody BibleDTO bibleDTO) {
    	System.out.println("실행");
        List<Map<String, Object>> result = bibleService.getSelectedContent(bibleDTO);
        Integer maxChapter = bibleService.getMaxChapter(bibleDTO.getList());

        // 한 번에 "maxChapter"와 그 값을 포함하는 맵을 생성하여 리스트에 추가
        result.add(Collections.singletonMap("maxChapter", (Object) maxChapter));
        System.out.println(result);
        return ResponseEntity.ok(result);
    }

    
    @PostMapping("/prevList")
    public ResponseEntity<Map<String, Object>> prevList(@RequestBody BibleDTO bibleDTO) {
    	Map<String, Object> map = bibleService.prevList(bibleDTO.getList());
    	return ResponseEntity.ok(map);
    }
    
    @PostMapping("/nextList")
    public ResponseEntity<Map<String, Object>> nextList(@RequestBody BibleDTO bibleDTO) {
    	Map<String, Object> map = bibleService.nextList(bibleDTO.getList());
    	return ResponseEntity.ok(map);
    }
    //@GetMapping("/getBibleListMaxChapter")
    //public ResponseEntity<List<Map<String, Object>>> getBibleListMaxChapter(){
    //	List<Map<String, Object>> map = bibleService.getBibleListMaxChapter();
    //	System.out.println(map);
    // 	return ResponseEntity.ok(map);
    //}
    
    //@PostMapping("/getMaxChapter")
    //public ResponseEntity<String> getMaxChapter(@RequestBody String list) {
    //	String maxChapter = bibleService.getMaxChapter(list);
    //	System.out.println(maxChapter);
    //	return ResponseEntity.ok(maxChapter);
    //}

}
