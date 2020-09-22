package com.myboard.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.myboard.dto.BoardDTO;
import com.myboard.dto.PageDTO;

@Repository
public class BoardDAOImpl implements BoardDAO{

	@Resource
	private SqlSession session;

	//전체 건수
	@Override
	public int totolCount(PageDTO pdto) throws Exception {
		return session.selectOne("boardMapper.totCount", pdto);
	}

	//전체 조회
	@Override
	public List<BoardDTO> selectList(PageDTO pdto) throws Exception {
		return session.selectList("boardMapper.selectList", pdto);
	}
	//한건조회
	@Override
	public BoardDTO selectOne(int bnum) throws Exception {
		return session.selectOne("boardMapper.selectOne", bnum);
	}

	//추가
	@Override
	public int insert(BoardDTO bdto) throws Exception {
		return session.insert("boardMapper.insert",bdto );
	}
	//수정
	@Override
	public int update(BoardDTO bdto) throws Exception {
		return session.update("boardMapper.update",bdto);
	}
	//삭제
	@Override
	public int delete(int bnum) throws Exception {
		return session.delete("boardMapper.delete",bnum);
	}
	//조회수 +1
	@Override
	public int readcnt_update(int bnum) throws Exception {
		return session.update("boardMapper.readcnt_update", bnum);
	}
	//댓글수 +1 
	@Override
	public int replycntUp_update(int bnum) throws Exception {
		return session.update("boardMapper.replycntUp_update", bnum);
	}

	//댓글수 -1 
	@Override
	public int replycntDown_update(int bnum) throws Exception {
		return session.update("boardMapper.replycntDown_update", bnum);
	}

}
