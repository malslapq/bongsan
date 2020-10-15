package com.myboard.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.myboard.dto.MemberDTO;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.web.multipart.MultipartFile;

public interface MemberService {
	public Map<String, Object> insert(MemberDTO dto, MultipartFile photofile) throws Exception;
	public Map<String, Object> update(MemberDTO dto, String oldpasswd, MultipartFile photofile) throws Exception;
	public Map<String, Object> delete(MemberDTO dto, String oldpasswd);
	public MemberDTO selectOne(String userid);
	public Map<String, Object> pwUpdate(MemberDTO dto, String oldpasswd);
	public int passCheck(String userid, String oldpasswd);
}
