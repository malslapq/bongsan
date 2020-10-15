package com.myboard.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.myboard.dao.BFileDAO;
import com.myboard.dto.BFileDTO;

@Service
public class BFileServiceImpl implements BFileService{
	//servlet-context.xml에 파일 저장 디렉토리 빈ㄴ
	@Resource(name="saveDir")
	String saveDir; 

	@Resource
	BFileDAO fdao;
	
	// 다중 파일 업로드하고 파일이름 리스트 반환
	@Override
	public List<String> fileUpload(List<MultipartFile> files) {
		
		List<String> list = new ArrayList<>();
		for(MultipartFile mf: files) {
			//파일이름 생성
			if (mf.getOriginalFilename() != "") { //파일명이 존재할때만 반복
				String filename = System.currentTimeMillis()+mf.getOriginalFilename();
				//전송할 파일경로와 이름 생성
				File f = new File(saveDir, filename);
				try {
					mf.transferTo(f);
					list.add(filename); //파일이름 추가
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return list;

	}

	//프로필사진 저장 및 사진명 리턴
	@Override
	public String profileUpload(MultipartFile file) {
		//파일 이름
		String filename = null;
		try {
			//유일한 번호 생성
			filename = System.currentTimeMillis() + file.getOriginalFilename();
			//
			File f = new File(saveDir, filename);
			System.out.println(saveDir+ " " + filename);
			file.transferTo(f);
		} catch (IllegalStateException e) {
			filename = null;
			e.printStackTrace();
		} catch (IOException e) {
			filename = null;
			e.printStackTrace();
		}
		return filename;
	}
	
	//파일명 저장
	@Override
	public int insert(int bnum, List<String> filenameList) throws Exception {
		for(String filename : filenameList) {
			BFileDTO fdto = new BFileDTO();
			fdto.setBnum(bnum);
			fdto.setFilename(filename);
			fdao.insert(fdto);
		}
		return 0;
	}

	//파일 리스트
	@Override
	public List<BFileDTO> selectList(int bnum) throws Exception {
		return fdao.selectList(bnum);
	}

	@Override
	public int delete(int bnum) throws Exception {
		return fdao.delete(bnum);
	}
	
	//수정시 일부 파일 삭제
	@Override
	public int delete_part(int bnum, List<Integer> fnumList) throws Exception{
		String fnums = "";
		//null처리
		if (fnumList != null) {
			fnums = fnumList.toString().replace("[", "").replace("]", "");
		}
		fdao.delete_part(bnum, fnums);

		return 0;
	}

	//파일 다운로드
	@Override
	public void fileDownload(String filename, HttpServletResponse response) throws Exception{
		String fileUrl = saveDir + "/" +filename;

		try {
			//파일 읽기 스트림 생성
			FileInputStream fis = new FileInputStream(fileUrl);
			
			//한글파일이름 인코딩
			filename = URLEncoder.encode(filename, "utf-8");
			//filename = new String(filename.getBytes("utf-8"), "iso-8859-1");
			
			//응답객체의 헤더설정 변경
			//첨부파일 형태로 변경
			//파일이름 지정
			response.setHeader("Content-Disposition", "attachment;filename="+filename);
			
			
			//파일 내보내기 스트림  생성
			OutputStream out = response.getOutputStream();
			//inputstream에서 데이타를 읽어서 outputstream으로 내보냄
			FileCopyUtils.copy(fis, out);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
