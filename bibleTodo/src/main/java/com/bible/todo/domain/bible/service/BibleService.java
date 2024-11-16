package com.bible.todo.domain.bible.service;

import org.springframework.stereotype.Service;

import com.bible.todo.domain.bible.mapper.BibleMapper;
import com.bible.todo.domain.bible.vo.BibleVo;

import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BibleService {
	private final BibleMapper bibleMapper;
	
	public String getBible(int bible_id) {
		return bibleMapper.getBible(bible_id);
	}
	public List<String> getTestamentList(String testament) {
		BibleVo bibleVo = new BibleVo();
		bibleVo.setTestament(testament);
		return bibleMapper.getTestamentList(bibleVo);
	}
	
}
