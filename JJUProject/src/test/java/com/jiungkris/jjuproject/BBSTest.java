package com.jiungkris.jjuproject;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jiungkris.jjuproject.dao.BBSDAO;
import com.jiungkris.jjuproject.vo.BBSVO;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class BBSTest {
	
    @Inject
    private BBSDAO dao;
    
 //   @Test
    public void testCreate() {
    	BBSVO vo = new BBSVO();
    	vo.setB_title("test");
    	vo.setB_content("test");
    	vo.setB_writer("test");
    	try {
			dao.create(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
//	@Test
    public void testList() {
    	try {
			System.out.println(dao.list());
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    //@Test
    public void testRead() throws Exception {
    	System.out.println(dao.read(2));
    }
    
    //@Test
    public void testDelete() throws Exception {
    	dao.delete(2);
    }
    
    @Test
    public void testUpdate() throws Exception {
    	BBSVO vo = new BBSVO();
    	vo.setB_title("update");
    	vo.setB_content("update");
    	vo.setB_no(3);
    	
    	dao.update(vo);
    }
}