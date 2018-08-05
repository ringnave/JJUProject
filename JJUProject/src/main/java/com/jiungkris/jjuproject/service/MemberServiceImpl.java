package com.jiungkris.jjuproject.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.jiungkris.jjuproject.dao.MemberDAO;
import com.jiungkris.jjuproject.vo.MemberVO;

@Service
public class MemberServiceImpl implements MemberService {
	
    @Inject
    private MemberDAO dao;
     
    @Override
    public MemberVO login(MemberVO vo) {
        return dao.login(vo);
    }

	@Override
	public void join(MemberVO vo) {
		dao.join(vo);
	}
	
	@Override
	public boolean idCheck(String id) {
		return dao.idCheck(id);
	}

	@Override
	public void deactivate(String id) {
		dao.deactivate(id);
	}
}