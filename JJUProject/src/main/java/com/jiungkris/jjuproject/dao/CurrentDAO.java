package com.jiungkris.jjuproject.dao;

import java.util.List;

import com.jiungkris.jjuproject.vo.MemberVO;

public interface CurrentDAO {
	public void login(String id);
	
	public void logout(String id);
	
	public int getNumberOfCurrentUsers();
	
	public List<MemberVO> getCurrentUsers();
}
