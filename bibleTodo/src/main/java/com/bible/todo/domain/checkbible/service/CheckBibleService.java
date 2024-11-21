package com.bible.todo.domain.checkbible.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.bible.todo.domain.bible.vo.BibleVo;
import com.bible.todo.domain.checkbible.dto.CheckBibleDTO;
import com.bible.todo.domain.checkbible.mapper.CheckBibleMapper;
import com.bible.todo.domain.checkbible.vo.CheckBibleVo;
import com.bible.todo.domain.comments.mapper.CommentMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CheckBibleService {
    private final CheckBibleMapper checkBibleMapper;
    private final CommentMapper commentMapper;

    public void checkVerse(CheckBibleDTO checkBibleDTO) {

        // BibleVo 객체에 필요한 정보 설정
        BibleVo bibleVo = new BibleVo();
        bibleVo.setList(checkBibleDTO.getList());
        bibleVo.setChapter(checkBibleDTO.getChapter());
        bibleVo.setVerse(checkBibleDTO.getVerse());

        // bible_id 가져오기
        int bible_id = commentMapper.getBibleId(bibleVo); // 해당 bible_id를 가져와 bible_id에 넣었음
        System.out.println("가져온 bible id값 : " + bible_id);

        // 해당 bible_id에 대한 체크 여부 확인
        hasCheck(checkBibleDTO, bible_id);
    }

    public void hasCheck(CheckBibleDTO checkBibleDTO, int bible_id) {
        // 사용자에 대한 체크 여부 정보를 담을 객체 생성
        CheckBibleVo checkBibleVo = new CheckBibleVo();
        checkBibleVo.setUser_id(checkBibleDTO.getUser_id());

        // 사용자의 bible_id와 읽음 유무 정보 가져오기
        List<Map<String, Object>> list = checkBibleMapper.hasCheck(checkBibleVo); // 해당 사용자의 bible_id와 읽음 유무
        System.out.println("hasCheck 실행, " + list + " list크기: " + list.size());

        // 만약 리스트가 비어 있으면, 즉 처음 체크하는 경우
        if (list.isEmpty()) {
            System.out.println("하나도 없어서 실행");
            checkBibleVo.setBible_id(bible_id);
            checkBibleVo.setUser_id(checkBibleDTO.getUser_id());
            checkBibleMapper.checkVerse(checkBibleVo); // 구절 체크
        } else {
            boolean isAlreadyChecked = false;

            // 리스트에서 bible_id가 존재하는지 확인
            for (Map<String, Object> map : list) {
                if (map.containsKey("bible_id") && map.get("bible_id").equals(bible_id)) {
                    System.out.println("이미 존재해서 실행 안했다 쀼쀼");
                    isAlreadyChecked = true;
                    break; // 이미 체크한 구절이므로 루프 종료
                }
            }

            // 만약 체크되지 않은 구절이 있다면 체크를 추가
            if (!isAlreadyChecked) {
                System.out.println("존재 안 해서 실행");
                checkBibleVo.setBible_id(bible_id);
                checkBibleVo.setUser_id(checkBibleDTO.getUser_id());
                checkBibleMapper.checkVerse(checkBibleVo); // 구절 체크
            }
        }
    }
}
