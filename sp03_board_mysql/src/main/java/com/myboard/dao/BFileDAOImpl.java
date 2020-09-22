package com.myboard.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.myboard.dto.BFileDTO;

@Repository
public class BFileDAOImpl implements BFileDAO {

	@Resource 
	private SqlSession session;

	@Override
	public List<BFileDTO> selectList(int bnum) throws Exception {
		return session.selectList("bfileMapper.selectList", bnum);
	}

	@Override
	public int insert(BFileDTO fdto) throws Exception {
		return session.insert("bfileMapper.insert", fdto);
	}

	@Override
	public int update(BFileDTO fdto) throws Exception {
		return session.update("bfileMapper.update", fdto);
	}

	@Override
	public int delete(int bnum) throws Exception {
		return session.delete("bfileMapper.delete", bnum);
	}

	@Override
	public int procTest(BFileDTO fdto) throws Exception {
		// TODO Auto-generated method stub
		return session.update("bfileMapper.procTest", fdto);
	}
	
	//수정시 일부파일 삭제
	@Override
	public int delete_part(int bnum, String fnums) {
		Map<String, Object> map = new HashMap<>();
		map.put("bnum",bnum );
		map.put("fnums",fnums );
		return session.delete("bfileMapper.delete_part", map);
	}
}
