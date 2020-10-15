package com.myboard.spring;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.myboard.dao.BoardDAO;
import com.myboard.dto.BoardDTO;
import com.myboard.dto.PageDTO;
import com.myboard.service.LoginServiceimple;

/*스프링 테스트*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class bdaoTest {

	@Resource
	private BoardDAO bdao;
	
	@Resource
	private LoginServiceimple lservice;
	
	@Test
	private void assertEquals() {
		
	}
	
//	@Test
//	public void testTotolCount() throws Exception {
//		PageDTO pdto = new PageDTO();
//		int totCnt = bdao.totolCount(pdto);
//		Assert.assertEquals(250,totCnt);
//	}
//
//	@Test
//	public void testSelectList() throws Exception {
//		PageDTO pdto = new PageDTO();
//		pdto.setStartNo(11);
//		pdto.setEndNo(20);
//		List<BoardDTO> blist = bdao.selectList(pdto);
//		Assert.assertNotNull(blist);
//	}
//
//	@Test
//	public void testSelectOne() throws Exception {
//		Assert.assertNotNull(bdao.selectOne(3));
//	}
//
//	@Test
//	public void testInsert() throws Exception {
//		BoardDTO bdto = new BoardDTO();
//		bdto.setWriter("홍길동");
//		bdto.setEmail("hong@gmail.com");
//		bdto.setSubject("제목");
//		bdto.setContent("내용");
//		//bdao.insert(bdto) 의 결과값이 1과 같으면 성공
//		Assert.assertEquals(1, bdao.insert(bdto));
//	}
//
//	@Test
//	public void testUpdate() throws Exception {
//		BoardDTO bdto = new BoardDTO();
//		bdto.setBnum(3);
//		bdto.setWriter("홍길동");
//		bdto.setEmail("hong@gmail.com");
//		bdto.setSubject("제목수정");
//		bdto.setContent("내용수정");
//		Assert.assertEquals(1, bdao.update(bdto));
//	}
//
//	@Test
//	public void testDelete() throws Exception {
//		Assert.assertEquals(1, bdao.delete(5));
//	}
//
//	@Test
//	public void testReadcnt_update() throws Exception {
//		Assert.assertEquals(1, bdao.readcnt_update(3));
//	}
//
//	@Test
//	public void testReplycntUp_update() throws Exception {
//		Assert.assertEquals(1, bdao.replycntUp_update(3));
//	}
//
//	@Test
//	public void testReplycntDown_update() throws Exception {
//		Assert.assertEquals(1, bdao.replycntDown_update(3));
//	}

}
