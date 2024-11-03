package com.bible.todo.domain.bible.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bible.todo.domain.bible.mapper.BibleMapper;

@Service
public class BibleService {
	private final BibleMapper bibleMapper;
	
	@Autowired
	public BibleService(BibleMapper bibleMapper) {
		this.bibleMapper = bibleMapper;
	}
	public String getBible(int bible_id) {
		 System.out.println("서비스bible_id = " + bible_id);
		return bibleMapper.getBible(bible_id);
	}
}
