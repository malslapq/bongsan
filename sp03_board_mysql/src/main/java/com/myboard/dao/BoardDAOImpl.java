package com.myboard.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.myboard.dto.BoardDTO;
import com.myboard.dto.BoardLikeDTO;
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
	
	@Override
	public BoardLikeDTO likecheck(BoardLikeDTO ldto) throws Exception {
		return session.selectOne("boardMapper.likecheck", ldto);
	}

	@Override
	public int likeinsert(BoardLikeDTO ldto) throws Exception {
		return session.insert("boardMapper.likeinsert", ldto);
	}

	@Override
	public int dislikeinsert(BoardLikeDTO ldto) throws Exception {
		return session.insert("boardMapper.dislikeinsert", ldto);
	}

	@Override
	public int likeupdate(BoardLikeDTO ldto) throws Exception {
		return session.update("boardMapper.likeupdate", ldto);
	}
	
	@Override
	public int dislikeupdate(BoardLikeDTO ldto) throws Exception {
		return session.update("boardMapper.dislikeupdate", ldto);
	}

	//board테이블의 like dislike 업데이트
	@Override
	public int boardLcntupdate(BoardDTO dto) throws Exception {
		return session.update("boardMapper.boardLcntupdate", dto);
	}

	@Override
	public int boardDlcntupdate(BoardDTO dto) throws Exception {
		return session.update("boardMapper.boardDLcntupdate", dto);
	}

	@Override
	public int likedelete(int bnum) throws Exception {
		return session.delete("boardMapper.boardlikedelete", bnum);
	}


	

}
