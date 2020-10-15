package com.myboard.controller;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myboard.dto.MemberDTO;
import com.myboard.service.MemberServiceImpl;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	@Resource
	private MemberServiceImpl service;
	
	//회원가입폼(join.jsp)으로 이동
	@RequestMapping(value="/join", method = RequestMethod.GET)
	public String join(HttpServletRequest request) {
		return "member/join";
	}
	
	//회원가입 db에 저장후 메인으로 이동
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(MemberDTO dto, MultipartFile photofile,RedirectAttributes rattr) throws Exception {
		//회원사진 파일 처리
		Map<String, Object> resultMap = service.insert(dto, photofile);
		rattr.addFlashAttribute("msg", resultMap.get("msg"));
		
		if ((int)resultMap.get("result") == 0) { //회원가입 성공
			return "redirect:/login/";
		}else {  //회원가입 실패
			return "redirect:/member/join";
		}
		
	}
	
	//회원수정폼보이기
	@RequestMapping(value="/modify", method = RequestMethod.GET)
	public String modify(HttpSession session, Model model) {
		MemberDTO dto = service.selectOne((String)session.getAttribute("userid"));
		model.addAttribute("dto", dto);
		return "member/modify";
	}
	
	//회원수정하기
	@RequestMapping(value="/modify", method = RequestMethod.POST)
	public String modify(MemberDTO dto,String oldpasswd,
			MultipartFile photofile, Model model) throws Exception {
		Map<String, Object> resultMap = service.update(dto, oldpasswd, photofile);
		//사진변경했을경우 대비해서 회원정보 다시 조회
		MemberDTO rdto = service.selectOne(dto.getUserid());
		model.addAttribute("dto", rdto);
		model.addAttribute("msg", resultMap.get("msg"));
		
		return "member/modify";
	}

	//회원 삭제하기
	@RequestMapping(value="/delete", method = RequestMethod.POST)
	public String delete(@ModelAttribute("dto") MemberDTO dto,String oldpasswd, 
			RedirectAttributes rattr, Model model) {
		Map<String, Object> resultMap = service.delete(dto, oldpasswd);
		
		if ((int)resultMap.get("result") == 0) { //삭제 성공시 
			rattr.addFlashAttribute("msg",resultMap.get("msg")); 
			return "redirect:/login/";			
		}else { //삭제실패시
			model.addAttribute("msg", resultMap.get("msg"));
			return "member/modify";			
		}
		

	}	
	
	//비밀번호 변경
	@RequestMapping("/pwUpdate")
	public String pwUpdate(@ModelAttribute("dto") MemberDTO dto,
			String oldpasswd, Model model) {
		//dto : 유저아이디 + 새로운 패스워드
		//oldpasswd : 기존패스워드
		Map<String, Object> resultMap = service.pwUpdate(dto,oldpasswd);
		
		model.addAttribute("msg",resultMap.get("msg"));
		return "member/modify";
	}
	
	
	
	
	
}
