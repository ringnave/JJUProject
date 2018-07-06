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
public class JoinTest {
	
    @Inject
    private MemberDAO dao;
    
    @Test
    public void test() {
    	MemberVO vo = new MemberVO();
    	
    	vo.setId("d");
    	vo.setPw("1");
    	vo.setName("jacki chan");
    	vo.setPhone("010-2222-1111");
    	vo.setEmail("sef@google.com");
    	
    	dao.join(vo);
    }
    
}