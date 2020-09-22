package com.myboard.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.json.simple.parser.ParseException;
import org.springframework.web.multipart.MultipartFile;

import com.myboard.dto.BoardDTO;
import com.myboard.dto.PageDTO;

public interface BoardService {
	//전체조회
	public List<BoardDTO> selectList(PageDTO pdto) throws Exception;
	//한건조회
	public Map<String, Object> selectOne(int bnum) throws Exception;
	//추가
	public int insert(BoardDTO bdto, List<MultipartFile> bfiles) throws Exception;
	//수정
	public int update(BoardDTO bdto, List<Integer> fnumList,List<MultipartFile> bfiles) throws Exception;
	//삭제
	public int delete(int bnum) throws Exception;
	//조회수 +1
	public int readcnt_update(int bnum) throws Exception;
	//댓글수 + 1
	public int replycntUp_update(int bnum) throws Exception;
	//댓글수 -1
	public int replycntDown_update(int bnum) throws Exception;
	//지도
	public Map<String, Double> getGeocoding(String addr) throws IOException, ParseException;
}
