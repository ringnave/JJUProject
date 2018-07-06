package com.jiungkris.jjuproject.service;

import java.util.List;

import com.jiungkris.jjuproject.vo.BBSVO;

public interface BBSService {
	
public void create(BBSVO vo) throws Exception;
	
	public List<BBSVO> list() throws Exception;
	
	public BBSVO read(Integer b_no) throws Exception;
	
	public void delete(Integer b_no) throws Exception;
	
	public void update(BBSVO vo) throws Exception;
	
}
