package com.bible.todo.domain.checkbible.service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
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
            else {
            
                System.out.println("업데이트 후 새로 체크하기");
                checkBibleVo.setBible_id(bible_id);
                checkBibleMapper.updateCheckVerse(checkBibleVo); // 업데이트
            }
        }
    }
    public Map<String, Object> getLastReading(CheckBibleDTO checkBibleDTO){
		CheckBibleVo checkBibleVo = new CheckBibleVo();
		checkBibleVo.setUser_id(checkBibleDTO.getUser_id());
		Map<String, Object> map = checkBibleMapper.getLastReading(checkBibleVo); 
		System.out.println("서비스 반환값" + map);
		return map;
	}
    
    public Map<String, Object> totalReading(CheckBibleDTO checkBibleDTO) {
    	CheckBibleVo checkBibleVo = new CheckBibleVo();
    	checkBibleVo.setUser_id(checkBibleDTO.getUser_id());
    	return checkBibleMapper.totalReading(checkBibleVo);
    	}
    
    public Map<String, Object> totalProgress(CheckBibleDTO checkBibleDTO) {
        // CheckBibleVo 생성 후 user_id 설정
        CheckBibleVo checkBibleVo = new CheckBibleVo();
        checkBibleVo.setUser_id(checkBibleDTO.getUser_id());
        
        // checkBibleMapper 호출하여 Map 반환
        Map<String, Object> map = checkBibleMapper.totalProgress(checkBibleVo);
        
        // progress_bar 값 가져오기 (BigDecimal로 반환된 경우)
        if (map.containsKey("progress_bar")) {
            Object progressBarObj = map.get("progress_bar");
            
            if (progressBarObj instanceof BigDecimal) {
                BigDecimal progressBar = (BigDecimal) progressBarObj;
                
                // BigDecimal을 Double로 변환
                Double progressValue = progressBar.doubleValue();
                
                // DecimalFormat을 사용하여 소수점 2자리로 포맷
                DecimalFormat df = new DecimalFormat("#.##");
                String formattedProgress = df.format(progressValue); // 포맷된 값 (문자열)
                
                // 포맷된 값을 다시 Map에 저장
                map.put("progress_bar", formattedProgress);
            }
        }
        
        return map;
    }
}