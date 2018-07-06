package com.jiungkris.jjuproject;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jiungkris.jjuproject.dao.MemberDAO;
import com.jiungkris.jjuproject.vo.MemberVO;
 
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class LoginDAOTest {
	
    @Inject
    private MemberDAO dao;
    
    @Test
    public void test() {
    	MemberVO vo = new MemberVO();
    	
    	vo.setId("abc");
    	vo.setPw("123");
    	
    	System.out.println(dao.login(vo));
    }
    
}