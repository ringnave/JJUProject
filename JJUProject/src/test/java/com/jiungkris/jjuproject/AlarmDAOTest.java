package com.jiungkris.jjuproject;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jiungkris.jjuproject.dao.AlarmDAO;
import com.jiungkris.jjuproject.vo.MemberVO;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class AlarmDAOTest {
	
    @Inject
    private AlarmDAO dao;
    
//    @Test
    public void insert() throws Exception {
    	MemberVO vo = new MemberVO();
    	vo.setId("ggg");
    	
    	dao.insertAccountInDB(vo);
    }
    
//    @Test
    public void delete() throws Exception {
    	MemberVO vo = new MemberVO();
    	vo.setId("ggg");
    	
    	dao.deleteAccountInDB(vo);
    }
    
//    @Test
    public void record() {
    	dao.recordAlarm("aaa", "bbb", 1);
    }
    
//    @Test
    public void read() {
    	System.out.println(dao.readAlarmCount("aaa", "bbb"));
    }
    
//    @Test
    public void deleteRecord() {
    	dao.removeAlarm("aaa", "bbb");
    }
    
    @Test
    public void list() {
    	System.out.println(dao.readForList("aaa").get(0).get("bbb"));
    }
}