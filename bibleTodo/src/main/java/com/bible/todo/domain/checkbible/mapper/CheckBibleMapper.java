package com.bible.todo.domain.checkbible.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.bible.todo.domain.bible.vo.BibleVo;
import com.bible.todo.domain.checkbible.vo.CheckBibleVo;

@Mapper
public interface CheckBibleMapper {
	
	void checkVerse(CheckBibleVo checkBibleVo);
	
	List<Map<String, Object>> hasCheck(CheckBibleVo checkBibleVo);
	
	Map<String, Object> getLastReading(CheckBibleVo checkbibleVo);
	
    void updateCheckVerse(CheckBibleVo checkBibleVo);
    
    Map<String, Object> totalReading(CheckBibleVo checkBibleVo);
    
    Map<String, Object> totalProgress(CheckBibleVo checkBibleVo);
    
    List<Double> checkListBible(CheckBibleVo checkBibleVo);
}
