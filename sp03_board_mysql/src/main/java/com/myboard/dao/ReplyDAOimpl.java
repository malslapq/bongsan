package com.myboard.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;

import com.myboard.dto.ReplyDTO;

@Repository
public class ReplyDAOimpl implements ReplyDAO{
	
//	@Resource
//	private SqlSessionFactory ssf;
	@Resource
	private SqlSession session;
	
	@Override
	public int insert(ReplyDTO rdto) {
		return session.insert("replyMapper.insert", rdto);
	}

	@Override
	public int update(ReplyDTO rdto) {
		return session.update("replyMapper.update", rdto);
	}

	@Override
	public int delete(int rnum) {
		return session.delete("replyMapper.delete", rnum);
	}

	@Override
	public List<ReplyDTO> selectList(int bnum) {
		return session.selectList("replyMapper.selectList",bnum);
	}

	@Override
	public ReplyDTO selectOne(int rnum) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteAll(int bnum) {
		return session.delete("replyMapper.deleteAll", bnum);
	}

}
