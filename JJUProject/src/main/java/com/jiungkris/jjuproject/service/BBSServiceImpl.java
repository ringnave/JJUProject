package com.jiungkris.jjuproject.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.jiungkris.jjuproject.dao.BBSDAO;
import com.jiungkris.jjuproject.vo.BBSVO;

@Service
public class BBSServiceImpl implements BBSService {

	@Inject
	private BBSDAO dao;
	
	@Override
	public void create(BBSVO vo) throws Exception {
		dao.create(vo);
	}

	@Override
	public List<BBSVO> list() throws Exception {
		return dao.list();
	}

	@Override
	public BBSVO read(Integer b_no) throws Exception {
		return dao.read(b_no);
	}

	@Override
	public void delete(Integer b_no) throws Exception {
		dao.delete(b_no);
	}

	@Override
	public void update(BBSVO vo) throws Exception {
		dao.update(vo);
	}

}
