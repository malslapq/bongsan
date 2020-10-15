package com.myboard.controller;

import java.io.Console;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myboard.dto.MemberDTO;
import com.myboard.dto.PageDTO;
import com.myboard.service.LoginServiceimple;
import com.myboard.service.MemberService;
import com.myboard.service.MemberServiceImpl;


@Controller
@RequestMapping("/login")
@SessionAttributes("pdto")
public class LoginController {
	
	//자동주입(객체를 변수에 대입)
	@Resource
	private LoginServiceimple service;
	
	@Resource
	private MemberServiceImpl mservice;
	
//	//login.jsp로 이동
//	@RequestMapping(value="/",method = RequestMethod.GET)
//	public String login() {
//		return "member/login";
//	}

	//login체크
	@RequestMapping(value="/check",method = RequestMethod.POST)
	public String login(String userid, String passwd, HttpSession session, 
			RedirectAttributes rattr,PageDTO pdto, Model model) {
		Map<String, Object> resultMap = service.loginCheck(userid, passwd);
		//서비스의 메시지 화면에 전달
		rattr.addFlashAttribute("msg", resultMap.get("msg"));
		int result = (int)resultMap.get("result");
		//redirect : 화면이동 주소 이동(@RequestMapping에 의한 이동만가능) 
		if (result == 0) { //로그인 성공시
			session.setAttribute("userid", userid); //세션에 아이디 저장
			session.setMaxInactiveInterval(60*60*2);
			model.addAttribute("pdto", pdto);
			return "redirect:/login/main";
		}else {
			return "redirect:/login/";
		}
	}

	
	//메인으로 이동
	@RequestMapping(value="/main")
	public String main() {
		return "board/main";
	}
	
	//로그아웃처리
	@RequestMapping(value="/logout")
	public String logout(HttpSession session, RedirectAttributes rattr ) {
		session.invalidate(); //세션정보 소멸
		rattr.addFlashAttribute("msg", "로그아웃되었습니다.");
		return "redirect:/login/";
	}
	
	//로그인폼이동
	@RequestMapping(value = "/")
	public String naverLogin(HttpSession session, Model model) throws UnsupportedEncodingException {
		Map<String, String> map = service.getLoginUrl();
		session.setAttribute("state", map.get("state"));
		model.addAttribute("apiURL", map.get("apiURL"));
		return "member/login";
	}
	
	
	@RequestMapping("/callback")
	public String callback(String code, String state, HttpSession session, Model model, PageDTO pdto) {
		String saveState = (String)session.getAttribute("state");
		MemberDTO dto = service.getUserInfo(code, saveState);
		if(saveState.equals(state)) {
			if (mservice.selectOne(dto.getUserid()) == null) {
				model.addAttribute("mdto",dto);
				return "member/callbackjoin";
			}
		}
		else {		
			System.out.println("인증 오류");
		}
		session.setAttribute("userid", dto.getUserid()); //세션에 아이디 저장
		session.setMaxInactiveInterval(60*60*2);
		model.addAttribute("pdto", pdto);
		return "redirect:/login/main";
	}
	
	
}
