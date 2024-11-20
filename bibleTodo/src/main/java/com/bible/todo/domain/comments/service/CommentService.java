package com.bible.todo.domain.comments.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.bible.todo.domain.bible.vo.BibleVo;
import com.bible.todo.domain.comments.dto.CommentDTO;
import com.bible.todo.domain.comments.mapper.CommentMapper;
import com.bible.todo.domain.comments.vo.CommentVo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentService {
	private final CommentMapper commentMapper;
	
	public void writeComment(CommentDTO commentDTO) {
		
		//bible ID 가져오기 
		BibleVo bibleVo = new BibleVo();
		bibleVo.setList(commentDTO.getList());
		bibleVo.setChapter(commentDTO.getChapter());
		bibleVo.setVerse(commentDTO.getVerse());
		System.out.println("서비스"+bibleVo);
		int bible_id = commentMapper.getBibleId(bibleVo);
		
		CommentVo commentVo = new CommentVo();
		// comment_id : 자동 증가, comment : 댓글, like_count : 좋아요 수, creation_date : 현재 시간, user_id : 사용자 ID, bible_id : 성경 번호
		commentVo.setComment(commentDTO.getComment());
		commentVo.setUser_id(commentDTO.getUser_id());
		commentVo.setBible_id(bible_id);
		
		commentMapper.writeComment(commentVo);
	}
	
	public List<Map<String, Object>> getComment(){
		return commentMapper.getComment();
	}
	
}
