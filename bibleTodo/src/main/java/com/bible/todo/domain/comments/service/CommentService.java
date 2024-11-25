package com.bible.todo.domain.comments.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.bible.todo.domain.bible.vo.BibleVo;
import com.bible.todo.domain.comments.dto.CommentDTO;
import com.bible.todo.domain.comments.mapper.CommentMapper;
import com.bible.todo.domain.comments.vo.CommentLikeVo;
import com.bible.todo.domain.comments.vo.CommentVo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentMapper commentMapper;

    // 댓글 작성
    public void writeComment(CommentDTO commentDTO) {
        BibleVo bibleVo = new BibleVo();
        bibleVo.setList(commentDTO.getList());
        bibleVo.setChapter(commentDTO.getChapter());
        bibleVo.setVerse(commentDTO.getVerse());
        System.out.println("서비스" + bibleVo);
        int bible_id = commentMapper.getBibleId(bibleVo);

        CommentVo commentVo = new CommentVo();
        commentVo.setComment(commentDTO.getComment());
        commentVo.setUser_id(commentDTO.getUser_id());
        commentVo.setBible_id(bible_id);

        commentMapper.writeComment(commentVo);
    }

    // 댓글 조회
    public List<Map<String, Object>> getComment() {
        return commentMapper.getComment();
    }

    //좋아요
    public void likeUp(CommentDTO commentDTO) {
        CommentLikeVo commentLikeVo = new CommentLikeVo();
        commentLikeVo.setComment_id(commentDTO.getComment_id());
        commentLikeVo.setUser_id(commentDTO.getUser_id());

        // 좋아요 여부 확인
        List<Map<String, Object>> list = commentMapper.isLike();

        // 좋아요가 없다면 새로 좋아요 추가
        if (list.isEmpty()) {
            System.out.println("좋아요 추가");
            commentMapper.likeUp(commentLikeVo); // 좋아요 추가
            commentLikeVo.setComment_id(commentDTO.getComment_id());
            System.out.println(commentLikeVo.getComment_id()+"추가1");
            commentMapper.updateLike(commentLikeVo); // 좋아요 수 업데이트
        } else {
            // 이미 좋아요를 눌렀다면 좋아요 취소
            boolean isLiked = list.stream()
                .anyMatch(entry -> entry.get("user_id").equals(commentDTO.getUser_id()) && 
                                   entry.get("comment_id").equals(commentDTO.getComment_id()));

            if (isLiked) {
                System.out.println("좋아요 취소");
                commentMapper.likeInit(commentLikeVo);  // 좋아요 초기화
                commentMapper.updateLike(commentLikeVo); // 좋아요 수 업데이트
                System.out.println(commentLikeVo.getComment_id()+"제거");
            } else {
                // 좋아요를 처음 누른 경우
                System.out.println("좋아요 추가");
                commentLikeVo.setComment_id(commentDTO.getComment_id());
                System.out.println(commentLikeVo.getComment_id()+"추가");
                commentMapper.likeUp(commentLikeVo);   // 좋아요 추가
                commentMapper.updateLike(commentLikeVo); // 좋아요 수 업데이트
            }
        }
    }
    public List<Map<String, Object>> getMyComment(CommentDTO commentDTO){
    	CommentVo commentVo = new CommentVo();
    	commentVo.setUser_id(commentDTO.getUser_id());
    	return commentMapper.getMyComment(commentVo);
    }
    
    public List<Map<String, Object>> myLoveComment(CommentDTO commentDTO){
    	CommentLikeVo commentLikeVo = new CommentLikeVo();
    	commentLikeVo.setUser_id(commentDTO.getUser_id());
    	return commentMapper.myLoveComment(commentLikeVo);
    }
}



