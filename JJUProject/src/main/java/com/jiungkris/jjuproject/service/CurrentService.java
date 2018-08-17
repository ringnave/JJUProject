package com.jiungkris.jjuproject.service;

import java.util.List;

import com.jiungkris.jjuproject.vo.MemberVO;

public interface CurrentService {
	public void login(String id);
	
	public void logout(String id);
	
	public int getNumberOfCurrentUsers();
	
	public List<MemberVO> getCurrentUsers();
}
