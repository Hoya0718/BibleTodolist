package com.bible.todo.domain.bible.service;

import org.springframework.stereotype.Service;

import com.bible.todo.domain.bible.dto.BibleDTO;
import com.bible.todo.domain.bible.mapper.BibleMapper;
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
}
