package com.jiungkris.jjuproject;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jiungkris.jjuproject.dao.BBSDAO;
import com.jiungkris.jjuproject.dao.CommentDAO;
 
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class PagingTest {
	
    @Inject
    private BBSDAO dao;
    
    @Inject
    private CommentDAO commentDao;
    
    public void getCountTest() throws Exception {
    	System.out.println(dao.getCount());
    }
    
    public void pagingTest() throws Exception {
    	System.out.println(dao.paging(7, 10));
    }

    public void C_getCountTest() throws Exception {
    	System.out.println(commentDao.getCount(501));
    }
    @Test
    public void C_pagingTest() throws Exception {
    	System.out.println(commentDao.paging(501, 1, 2));
    }
}