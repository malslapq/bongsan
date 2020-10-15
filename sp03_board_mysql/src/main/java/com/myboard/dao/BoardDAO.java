package com.myboard.dao;

import java.util.List;

import com.myboard.dto.BoardDTO;
import com.myboard.dto.BoardLikeDTO;
import com.myboard.dto.PageDTO;

public interface BoardDAO {
	//전체 건수
	public int totolCount(PageDTO pdto) throws Exception;
	//전체조회
	public List<BoardDTO> selectList(PageDTO pdto) throws Exception;
	//한건조회
	public BoardDTO selectOne(int bnum) throws Exception;
	//추가
	public int insert(BoardDTO bdto) throws Exception;
	//수정
	public int update(BoardDTO bdto) throws Exception;
	//삭제
	public int delete(int bnum) throws Exception;
	//조회수 +1
	public int readcnt_update(int bnum) throws Exception;
	//댓글수 + 1
	public int replycntUp_update(int bnum) throws Exception;
	//댓글수 -1
	public int replycntDown_update(int bnum) throws Exception;
	//좋아요 추가
	public int likeinsert(BoardLikeDTO ldto) throws Exception;
	//싫어요 추가
	public int dislikeinsert(BoardLikeDTO ldto) throws Exception;
	//boardlike 업데이트
	public int likeupdate(BoardLikeDTO ldto) throws Exception;
	public int dislikeupdate(BoardLikeDTO ldto) throws Exception;
	//board테이블의 likecnt 업데이트
	public int boardLcntupdate(BoardDTO dto) throws Exception;
	public int boardDlcntupdate(BoardDTO dto) throws Exception;
	//boardlike삭제
	public int likedelete(int bnum) throws Exception;
	//like dislike 확인
	public BoardLikeDTO likecheck(BoardLikeDTO ldto) throws Exception;
}
