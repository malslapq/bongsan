package com.myboard.dao;

import java.util.List;

import com.myboard.dto.MemberDTO;


//자식클래스가 정의해야 하는 메소드를 포함
public interface MemberDAO {
	//전체조회
	public List<MemberDTO> selectList();
	//한건조회
	public MemberDTO selectOne(String userid);
	//추가
	public int insert(MemberDTO dto);
	//수정
	public int update(MemberDTO dto);
	//삭제
	public int delete(String userid);
	//패스워드 수정
	public int updatePw(MemberDTO dto);
	//중복체크
	public int idcheck(String userid);
}
