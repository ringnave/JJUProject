package com.jiungkris.jjuproject.service;

import com.jiungkris.jjuproject.vo.MemberVO;

public interface MemberService {
	
	public MemberVO login(MemberVO vo);
	
	public void join(MemberVO vo);
	
	public boolean idCheck(String id);

	public void deactivate(String id);
}
