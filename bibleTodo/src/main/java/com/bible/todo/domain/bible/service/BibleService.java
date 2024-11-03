package com.bible.todo.domain.bible.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bible.todo.domain.bible.mapper.BibleMapper;
import com.bible.todo.domain.bible.vo.BibleVo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Service
public class BibleService {
	private final BibleMapper bibleMapper;
	
	@Autowired
	public BibleService(BibleMapper bibleMapper) {
		this.bibleMapper = bibleMapper;
	}
	
	public String getBible(int bible_id) {
		return bibleMapper.getBible(bible_id);
	}

}
