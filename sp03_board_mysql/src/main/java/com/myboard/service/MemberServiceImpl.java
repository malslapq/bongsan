package com.myboard.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.myboard.dao.MemberDAO;
import com.myboard.dto.MemberDTO;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class MemberServiceImpl implements MemberService{
	
	@Resource
	private MemberDAO dao;
	
	@Resource
	private BFileService fservice;
	
	
	@Resource
	private BCryptPasswordEncoder encode;
	
	@Override
	public Map<String, Object> insert(MemberDTO dto, MultipartFile photofile) throws Exception {
		String msg = null;
		int result = -1;
		System.out.println(dto);
		//아이디 중복 체크
		//저장성공하면 result :0, 실패 result :1
		MemberDTO rdto = dao.selectOne(dto.getUserid());
		
		if (rdto == null) { //기존 아이디 미존재
			//암호화 처리
			dto.setPasswd(encode.encode(dto.getPasswd())); //평문암호를 암호문으로 변경
			//회원사진 저장하고 저장된 파일 이름 반환
			String filename = fservice.profileUpload(photofile);
			dto.setFilename(filename);
			
			//db에 저장
			int cnt = dao.insert(dto);
			msg = "회원가입 완료";
			result = 0;

		}else { //아이디 존재
			msg = "아이디가 존재합니다.";
			result = -1;
		}
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("msg", msg);
		resultMap.put("result", result);
		return resultMap;
	}
	
	//회원수정
	@Override
	public Map<String, Object> update(MemberDTO dto, String oldpasswd, MultipartFile photofile) throws Exception {
		String msg = null;
		int result = -1;
		if (passCheck(dto.getUserid(), oldpasswd) == 0) {//패스워드 일치한다면
			//사진파일을 변경했다면
			if (photofile != null) {
				String filename = fservice.profileUpload(photofile);
				dto.setFilename(filename);
			}
			int cnt = dao.update(dto);
			if (cnt >0) {
				result = 0;
				msg = "수정이 완료되었습니다";
			}else {
				result = 1;
				msg = "수정 에러";
			}
		}else {
			msg = "패스워드 불일치";
			result = 1;
		}
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("msg", msg);
		resultMap.put("result", result);
		return resultMap;
	}
	
	//회원삭제
	@Override
	public Map<String, Object> delete(MemberDTO dto, String oldpasswd) {
		String msg = null;
		int result = -1;
		if (passCheck(dto.getUserid(), oldpasswd) == 0) {//패스워드 일치한다면
			int cnt = dao.delete(dto.getUserid());
			if (cnt >0) {
				result = 0;
				msg = "삭제가 완료되었습니다";
			}else {
				result = 1;
				msg = "삭제 에러";
			}
		}else {
			msg = "패스워드 불일치";
			result = 1;
		}
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("msg", msg);
		resultMap.put("result", result);
		return resultMap;
	}

	//회원조회
	@Override
	public MemberDTO selectOne(String userid) {
		return dao.selectOne(userid);
	}
	
	//비밀번호 변경
	@Override
	public Map<String, Object> pwUpdate(MemberDTO dto, String oldpasswd) {
		String msg = null;
		int result = -1;
		if (passCheck(dto.getUserid(), oldpasswd) == 0) { //패스워드 일치하면
			//평문암호를 암호문으로 변경
			dto.setPasswd(encode.encode(dto.getPasswd())); 
			dao.updatePw(dto);
			msg = "비밀번호가 변경되었습니다.";
			result = 0;
		}else {
			msg = "패스워드 불일치";
			result = 1;
		}
		
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("msg", msg);
		resultMap.put("result", result);
		return resultMap;
	}
	
	//패스워드 일치 여부 체크
	@Override
	public int passCheck(String userid, String oldpasswd) {
		MemberDTO dto = dao.selectOne(userid);
		String msg = null;
		int result = -1;
		if (encode.matches(oldpasswd, dto.getPasswd())) { //패스워드 일치
			result = 0;
		}else {  //패스워드 불일치
			result = 1;
		}
		
		return result;

	}
	
	
	
	
}
