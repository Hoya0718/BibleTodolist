//package com.bible.todo.domain.oauth2.controller;
//
//import java.util.Map;
//
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import jakarta.servlet.http.HttpSession;
//
//@RequestMapping("/api")
//@RestController
//public class OAuth2Controller {
//
//    // 세션에서 사용자 정보를 반환
//    @PostMapping("/oauth2/user")
//    public ResponseEntity<Map<String, Object>> getUserInfo(HttpSession session) {
//        // 세션에서 user 객체를 가져옵니다
//        Object user = session.getAttribute("user");
//        System.out.println("이게 바로 유저 " + user);
//
//        if (user == null) {
//            return ResponseEntity.status(401).body(Map.of("error", "User not authenticated"));
//        }
//
//        // user 객체가 존재하면 이를 반환 (일반적으로 OAuth2User 객체를 반환)
//        return ResponseEntity.ok(Map.of("user_id", user));
//    }
//}
