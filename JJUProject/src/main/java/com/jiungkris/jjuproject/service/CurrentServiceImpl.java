package com.jiungkris.jjuproject.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.jiungkris.jjuproject.dao.CurrentDAO;
import com.jiungkris.jjuproject.vo.MemberVO;

@Service
public class CurrentServiceImpl implements CurrentService {

	@Inject
	private CurrentDAO dao;
	
	@Override
	public void login(String id) {
		dao.login(id);
	}

	@Override
	public void logout(String id) {
		dao.logout(id);
	}

	@Override
	public int getNumberOfCurrentUsers() {
		return dao.getNumberOfCurrentUsers();
	}

	@Override
	public List<MemberVO> getCurrentUsers() {
		return dao.getCurrentUsers();
	}

}
