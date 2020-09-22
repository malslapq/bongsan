package com.myboard.dao;

import java.util.List;

import com.myboard.dto.BFileDTO;

public interface BFileDAO {
	//파일조회
	public List<BFileDTO> selectList(int bnum) throws Exception;
	//추가
	public int insert(BFileDTO fdto) throws Exception;
	//수정
	public int update(BFileDTO fdto) throws Exception;
	//삭제
	public int delete(int bnum) throws Exception;
	//수정시 일부파일 삭제
	public int delete_part(int bnum, String fnums);
	
	
	
	
	
	
	
	
	
	//프로시저 테스트
	public int procTest(BFileDTO fdto) throws Exception;
}
