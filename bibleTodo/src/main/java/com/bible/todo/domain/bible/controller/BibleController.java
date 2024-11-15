package com.bible.todo.domain.bible.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bible.todo.domain.bible.service.BibleService;

@RestController // JSON 형식으로 응답을 반환
@RequestMapping("/api")
public class BibleController {
    private final BibleService bibleService;

    @Autowired
    public BibleController(BibleService bibleService) {
        this.bibleService = bibleService;
    }

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
}
