package com.jiungkris.jjuproject.dao;

import com.jiungkris.jjuproject.vo.MemberVO;

public interface MemberDAO {
	
	public MemberVO login(MemberVO vo);
	
	public void join(MemberVO vo);
	
	public boolean idCheck(String id);
	
	public void deactivate(String id);
}
