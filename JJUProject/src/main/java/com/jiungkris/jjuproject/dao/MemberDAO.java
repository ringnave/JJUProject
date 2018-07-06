package com.jiungkris.jjuproject.dao;

import com.jiungkris.jjuproject.vo.MemberVO;

public interface MemberDAO {
	
	public MemberVO login(MemberVO vo);
	
	public void join(MemberVO vo);
}
