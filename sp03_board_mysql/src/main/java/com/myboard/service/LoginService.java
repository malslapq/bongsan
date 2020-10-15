package com.myboard.service;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import com.myboard.dto.MemberDTO;

public interface LoginService {
	public Map<String, Object> loginCheck(String userid, String passwd);
	public Map<String, String> getLoginUrl() throws UnsupportedEncodingException;
	
	//개인정보 조회
	public MemberDTO getUserInfo(String code, String state);
	public String getToken(String code, String state);
}
