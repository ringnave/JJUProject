package com.jiungkris.jjuproject;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jiungkris.jjuproject.dao.RecordDialogueDAO;
import com.jiungkris.jjuproject.vo.MemberVO;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class recordDialogueDAOTest {
	
    @Inject
    private RecordDialogueDAO dao;
    
//    @Test
    public void insert() throws Exception {
    	MemberVO vo = new MemberVO();
    	vo.setId("fff");
    	
    	dao.insertAccountInDB(vo);
    }
    
//    @Test
    public void delete() throws Exception {
    	MemberVO vo = new MemberVO();
    	vo.setId("bbb");
    	
    	dao.deleteAccountInDB(vo);
    }
    
    @Test
    public void record() {
    	dao.recordDialogue("aaa", "ddd", "message");
    }
    
//    @Test
    public void read() {
    	System.out.println(dao.readRecord("aaa", "bbb"));
    }
    
//    @Test
    public void deleteRecord() {
    	dao.removeRecord("aaa", "bbb");
    }
}