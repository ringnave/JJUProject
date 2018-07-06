package com.jiungkris.jjuproject.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.jiungkris.jjuproject.vo.BBSVO;

@Repository
public class BBSDAOImpl implements BBSDAO {

	@Inject
	private SqlSession sqlSession;
	
	private String namespace = "com.jiungkris.jjuproject.mappers.BBSMapper.";
	@Override
	public void create(BBSVO vo) throws Exception {
		sqlSession.insert(namespace + "create", vo);
	}

	@Override
	public List<BBSVO> list() throws Exception {
		return sqlSession.selectList(namespace + "list");
	}

	@Override
	public BBSVO read(Integer b_no) throws Exception {
		return sqlSession.selectOne(namespace + "read", b_no);
	}

	@Override
	public void delete(Integer b_no) throws Exception {
		sqlSession.delete(namespace + "delete", b_no);
	}

	@Override
	public void update(BBSVO vo) throws Exception {
		sqlSession.update(namespace + "update", vo);
	}

}
