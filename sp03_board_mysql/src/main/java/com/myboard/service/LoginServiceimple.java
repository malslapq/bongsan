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

import javax.annotation.Resource;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.myboard.dao.MemberDAO;
import com.myboard.dto.MemberDTO;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceimple implements LoginService{
	
	@Resource
	private MemberDAO dao;
	@Resource
	private BCryptPasswordEncoder encode;
	
	public Map<String, Object> loginCheck(String userid, String passwd) {
		//result : 0(로그인성공), 1(아이디미존재), 2(패스워드불일치)
		String msg = null;
		int result = -1;
		MemberDTO mdto = dao.selectOne(userid);
		System.out.println(mdto);
		if (mdto == null) { //아이디 없음
			msg = "아이디가 없습니다.";
			result = 1;
		}else {
			//평문을 암호화시킨 값과 db에 저장된 암호문과 비교
			if (encode.matches(passwd, mdto.getPasswd())) {
				msg = "로그인 성공";
				result = 0;
			}else {
				msg = "패스워드가 불일치";
				result = 2;
			}
		}
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("msg", msg);
		resultMap.put("result", result);
		return resultMap;
	}


	public Map<String, String> getLoginUrl() throws UnsupportedEncodingException {
	    String clientId = "gslTPF0NDYls3Q76DW0M";//애플리케이션 클라이언트 아이디값";
	    String redirectURI = URLEncoder.encode("http://localhost:8081/spring_mysql/login/callback", "UTF-8");
	    SecureRandom random = new SecureRandom();
	    String state = new BigInteger(130, random).toString();
	    String apiURL = "https://nid.naver.com/oauth2.0/authorize?response_type=code"; //인증 코드값 요청
	    apiURL += "&client_id=" + clientId;
	    apiURL += "&redirect_uri=" + redirectURI;
	    apiURL += "&state=" + state;
	    Map<String, String> map = new HashMap<String, String>();
	    map.put("apiURL", apiURL);
	    map.put("state", state);
	    return map;
	}
	
	//개인정보 조회
	public MemberDTO getUserInfo(String code, String state) {
		String token = getToken(code, state); // 네이버 로그인 접근 토큰;
        String header = "Bearer " + token; // Bearer 다음에 공백 추가

        String apiURL = "https://openapi.naver.com/v1/nid/me";

        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("Authorization", header);
        String responseBody = get(apiURL,requestHeaders);
        
        //파싱 : 아이디 이메일 이름
        
        JSONObject data =  (JSONObject)JSONValue.parse(responseBody);
        JSONObject response = (JSONObject) data.get("response");
        String email = (String) response.get("email");
        int index = email.indexOf("@");
        String name = (String) response.get("name");
        String id = email.substring(0,index);
        MemberDTO dto = new MemberDTO();
        dto.setUserid(id);
        dto.setEmail(email);
        dto.setName(name);
        dto.setPasswd("naver");
        dto.setJoinflag(1);
        Map<String, String> resultmap = new HashMap<String, String>();
        resultmap.put("id", id);
        resultmap.put("email", email);
        resultmap.put("name", name);
        return dto;
	}
	
	private static String get(String apiUrl, Map<String, String> requestHeaders){
        HttpURLConnection con = connect(apiUrl);
        try {
            con.setRequestMethod("GET");
            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
                return readBody(con.getInputStream());
            } else { // 에러 발생
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect();
        }
    }

    private static HttpURLConnection connect(String apiUrl){
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection)url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }

    private static String readBody(InputStream body){
        InputStreamReader streamReader = new InputStreamReader(body);

        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();

            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }

            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
        }
    }
	
	public String getToken(String code, String state) {
		 String clientId = "gslTPF0NDYls3Q76DW0M";//애플리케이션 클라이언트 아이디값";
		    String clientSecret = "4nNQF8DnF_";//애플리케이션 클라이언트 시크릿값";
//		    String code = (String)request.getAttribute("code");//인증 코드값
//		    String state = (String)request.getAttribute("state");//요청 pc에서 생성한 값
		    String apiURL;
		    
		    //토큰은 정보를 요청할 수 있는 값
		    apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&";
		    apiURL += "client_id=" + clientId;
		    apiURL += "&client_secret=" + clientSecret;
		    apiURL += "&code=" + code;
		    apiURL += "&state=" + state;
		    String access_token = "";
		    String refresh_token = "";
//		    System.out.println("apiURL="+apiURL);
		    try {
		      URL url = new URL(apiURL);
		      HttpURLConnection con = (HttpURLConnection)url.openConnection();
		      con.setRequestMethod("GET");
		      int responseCode = con.getResponseCode();
		      BufferedReader br;
		      System.out.print("responseCode="+responseCode);
		      if(responseCode==200) { // 정상 호출
		        br = new BufferedReader(new InputStreamReader(con.getInputStream()));
		      } else {  // 에러 발생
		        br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
		      }
		      String inputLine;
		      StringBuffer res = new StringBuffer();
		      while ((inputLine = br.readLine()) != null) {
		        res.append(inputLine);
		      }
		      br.close();
		      if(responseCode==200) {
		        System.out.println(res.toString());
		        //토큰 값 파싱
		        JSONObject data =  (JSONObject)JSONValue.parse(res.toString());
		        access_token = (String) data.get("access_token");
		      }
		    } catch (Exception e) {
		      System.out.println(e);
		    }
		    return access_token;
	}
	

}
