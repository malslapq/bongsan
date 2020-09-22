package com.myboard.spring;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.myboard.dao.BFileDAO;
import com.myboard.dto.BFileDTO;

/*스프링 테스트*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class fdaoTest {
	@Resource
	private BFileDAO fdao;
	
	@Test
	public void testSelectList() throws Exception {
		List<BFileDTO> list = fdao.selectList(3);
		System.out.println(list);
		Assert.assertNotNull(list);
	}

	@Test
	public void testInsert() throws Exception {
		BFileDTO fdto = new BFileDTO();
		fdto.setBnum(3);
		fdto.setFilename("aaa.jpg");
		fdao.insert(fdto);
	}

	@Test
	public void testUpdate() throws Exception {
		BFileDTO fdto = new BFileDTO();
		fdto.setBnum(3);
		fdto.setFnum(1);
		fdto.setFilename("bbb.jpg");
		fdao.update(fdto);
	}

	@Test
	public void testDelete() throws Exception {
		fdao.delete(3);
	}
	
	
	@Test
	public void testProc() throws Exception {
		BFileDTO fdto = new BFileDTO();
		fdto.setBnum(14);
		fdto.setFnum(18);
		fdto.setFilename("aaa.jpg");
		fdao.procTest(fdto);
	}
	

}
