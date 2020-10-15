package com.myboard.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;

import org.springframework.stereotype.Repository;

import com.myboard.dto.MemberDTO;

@Repository
public class MemberDAOImpl implements MemberDAO{
	@Resource
	private SqlSession session;
	
	//한건조회
	@Override
	public MemberDTO selectOne(String userid) {
		return session.selectOne("memberMapper.selectOne",userid);
	}
	
	//회원추가
	@Override
	public int insert(MemberDTO dto) {
		return session.insert("memberMapper.insert",dto);
	}
	
	//회원수정
	@Override
	public int update(MemberDTO dto) {
		return session.update("memberMapper.update", dto);
	}

	//전체조회
	@Override
	public List<MemberDTO> selectList() {
		// TODO Auto-generated method stub
		return null;
	}

	//삭제
	@Override
	public int delete(String userid) {
		return session.delete("memberMapper.delete", userid);
	}

	@Override
	public int updatePw(MemberDTO dto) {
		return session.update("memberMapper.updatePw", dto);
	}

	@Override
	public int idcheck(String userid) {
		return session.selectOne("memberMapper.usercheck",userid);
	}
	
	
	
	
	
	
}
