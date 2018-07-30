package com.jiungkris.jjuproject.dao;

import java.util.List;

import com.jiungkris.jjuproject.vo.BBSVO;
import com.jiungkris.jjuproject.vo.MemberVO;

public interface BBSDAO {

	public void create(BBSVO vo) throws Exception;
	
	public List<BBSVO> list() throws Exception;
	
	public BBSVO read(Integer b_no) throws Exception;
	
	public void delete(Integer b_no) throws Exception;
	
	public void update(BBSVO vo) throws Exception;
	
	public List<BBSVO> paging(int offset, int noOfRecords) throws Exception;
	
	public int getCount() throws Exception;
	
	public List<MemberVO> getIds() throws Exception;
}
