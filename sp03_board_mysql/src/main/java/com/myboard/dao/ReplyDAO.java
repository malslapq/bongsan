package com.myboard.dao;

import java.util.List;

import com.myboard.dto.ReplyDTO;

public interface ReplyDAO {

		public int insert(ReplyDTO rdto) throws Exception;
		public int update(ReplyDTO rdto) throws Exception;
		public int deleteAll(int bnum) throws Exception;
		public int delete(int rnum) throws Exception;
		public List<ReplyDTO> selectList(int bnum) throws Exception;
		public ReplyDTO selectOne(int rnum) throws Exception;
}
