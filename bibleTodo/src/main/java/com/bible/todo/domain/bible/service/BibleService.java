package com.bible.todo.domain.bible.service;

import org.springframework.stereotype.Service;

import com.bible.todo.domain.bible.dto.BibleDTO;
import com.bible.todo.domain.bible.dto.BibleLikeDTO;
import com.bible.todo.domain.bible.mapper.BibleMapper;
import com.bible.todo.domain.bible.vo.BibleLikeVo;
import com.bible.todo.domain.bible.vo.BibleVo;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BibleService {
	private final BibleMapper bibleMapper;

	public List<String> getTestament() {
		return bibleMapper.getTestament();
	}

	public String getBible(int bible_id) {
		return bibleMapper.getBible(bible_id);
	}

	public List<String> getTestamentList(String testament) {
		BibleVo bibleVo = new BibleVo();
		bibleVo.setTestament(testament);
		return bibleMapper.getTestamentList(bibleVo);
	}

	public List<String> getChapter(String chapter) {
		BibleVo bibleVo = new BibleVo();
		bibleVo.setList(chapter);
		return bibleMapper.getChapter(bibleVo);
	}

	public List<String> getVerse(BibleDTO bibleDTO) {
		BibleVo bibleVo = new BibleVo();
		bibleVo.setChapter(bibleDTO.getChapter());
		bibleVo.setList(bibleDTO.getList());
		return bibleMapper.getVerse(bibleVo);
	}

	public Map<String, Object> getContent(BibleDTO bibleDTO) {
		BibleVo bibleVo = new BibleVo();
		bibleVo.setChapter(bibleDTO.getChapter());
		bibleVo.setList(bibleDTO.getList());
		return bibleMapper.getContent(bibleVo);
	}

	public Map<String, Object> getSelectedContent(BibleDTO bibleDTO) {
		BibleVo bibleVo = new BibleVo();
		bibleVo.setChapter(bibleDTO.getChapter());
		bibleVo.setList(bibleDTO.getList());
		bibleVo.setVerse(bibleDTO.getVerse());
		Map<String, Object> resultMap = new HashMap<>();

		Map<String, Object> map1 = bibleMapper.getSelectedContent(bibleVo);
		Map<String, Object> map2 = bibleMapper.getMaxVerse(bibleVo);
		resultMap.putAll(map1);
		resultMap.putAll(map2);
		return resultMap;

	}

	public Integer getMaxChapter(String list) {
		BibleVo bibleVo = new BibleVo();
		bibleVo.setList(list);
		return bibleMapper.getMaxChapter(bibleVo);
	}

	public List<Map<String, Object>> getBibleListMaxChapter() {
		return bibleMapper.getBibleListMaxChapter();
	}

	public Map<String, Object> prevList(String list) {
		BibleVo bibleVo = new BibleVo();
		bibleVo.setList(list);
		return bibleMapper.prevList(bibleVo);
	}

	public Map<String, Object> nextList(String list) {
		BibleVo bibleVo = new BibleVo();
		bibleVo.setList(list);
		return bibleMapper.nextList(bibleVo);
	}

	public Map<String, Object> getMaxVerse(BibleDTO bibleDTO) {
		BibleVo bibleVo = new BibleVo();
		bibleVo.setList(bibleDTO.getList());
		bibleVo.setChapter(bibleDTO.getChapter());
		return bibleMapper.getMaxVerse(bibleVo);
	}

	public List<Map<String, Object>> SearchWord(BibleDTO bibleDTO) {
		BibleVo bibleVo = new BibleVo();
		bibleVo.setContent(bibleDTO.getContent());
		List<Map<String, Object>> list = bibleMapper.SearchWord(bibleVo);
		System.out.println("서비스 전달값" + bibleVo.getContent());
		System.out.println("서비스 반환값" + list);
		return list;
	}
	
	public void likeBible(BibleLikeDTO bibleLikeDTO) {
		BibleLikeVo bibleLikeVo = new BibleLikeVo();
		bibleLikeVo.setBible_id(bibleLikeDTO.getBible_id());
		bibleLikeVo.setUser_id(bibleLikeDTO.getUser_id());
		
		List<Map<String, Object>> list = bibleMapper.isLike(bibleLikeVo);
		
		// 좋아요가 없다면 새로 좋아요 추가
        if (list.isEmpty()) {
            System.out.println("좋아요 추가");
            bibleMapper.likeBible(bibleLikeVo); // 좋아요 추가
            bibleLikeVo.setBible_id(bibleLikeDTO.getBible_id());
            System.out.println(bibleLikeVo.getBible_id()+"추가1");
            bibleMapper.updateLike(bibleLikeVo); // 좋아요 수 업데이트
        } else {
            // 이미 좋아요를 눌렀다면 좋아요 취소
            boolean isLiked = list.stream()
                .anyMatch(entry -> entry.get("user_id").equals(bibleLikeDTO.getUser_id()) && 
                                   entry.get("bible_id").equals(bibleLikeDTO.getBible_id()));

            if (isLiked) {
                System.out.println("좋아요 취소");
                bibleMapper.likeInit(bibleLikeVo);  // 좋아요 초기화
                bibleMapper.updateLike(bibleLikeVo); // 좋아요 수 업데이트
                System.out.println(bibleLikeVo.getBible_id()+"제거");
            } else {
                // 좋아요를 처음 누른 경우
                System.out.println("좋아요 추가");
                bibleLikeVo.setBible_id(bibleLikeDTO.getBible_id());
                System.out.println(bibleLikeVo.getBible_id()+"추가");
                bibleMapper.likeBible(bibleLikeVo);   // 좋아요 추가
                bibleMapper.updateLike(bibleLikeVo); // 좋아요 수 업데이트
            }
        }
	}
	public List<Map<String, Object>> getMyLoveBible(BibleLikeDTO bibleLikeDTO){
		BibleLikeVo bibleLikeVo = new BibleLikeVo();
		bibleLikeVo.setUser_id(bibleLikeDTO.getUser_id());
		return bibleMapper.getMyLoveBible(bibleLikeVo);
	}
	
	public Map<String, Object> todayBible() {
		return bibleMapper.todayBible();
	}
	
}
