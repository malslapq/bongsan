package com.myboard.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.myboard.dto.BFileDTO;

public interface BFileService  {
	//파일 저장하고 파일명을 리턴
	public List<String> fileUpload(List<MultipartFile> files) throws Exception;
	//파일명 리스트를 입력받아 파일명 저장
	public int insert(int bnum, List<String> filenameList) throws Exception;
	//파일 리스트
	public List<BFileDTO> selectList(int bnum) throws Exception;
	//삭제
	public int delete(int bnum) throws Exception;
	//수정시 일부 파일 삭제
	public int delete_part(int bnum, List<Integer> fnumList) throws Exception;
	//파일 다운로드
	public void fileDownload(String filename, HttpServletResponse response) throws Exception;
	
	
}
