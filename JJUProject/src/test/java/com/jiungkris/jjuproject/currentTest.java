package com.jiungkris.jjuproject;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jiungkris.jjuproject.dao.CurrentDAO;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class currentTest {
	
    @Inject
    private CurrentDAO dao;
    
    
    public void testUpdate() throws Exception {
       	//dao.login("aaa");
       	dao.logout("aaa");
    }
    
    public void testCCU() throws Exception{
    	System.out.println(dao.getCurrentUsers());
    }
    
    @Test
    public void testCurrent() throws Exception {
    	System.out.println(dao.getCurrentUsers());
    }
}