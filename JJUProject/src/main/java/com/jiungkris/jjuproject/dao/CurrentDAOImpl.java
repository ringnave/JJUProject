package com.jiungkris.jjuproject.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.jiungkris.jjuproject.vo.MemberVO;

@Repository
public class CurrentDAOImpl implements CurrentDAO {

	@Inject
	private SqlSession sqlSession;
	
	private String namespace = "com.jiungkris.jjuproject.mappers.currentMapper.";
	
	@Override
	public void login(String id) {
		sqlSession.update(namespace + "login", id);
	}

	@Override
	public void logout(String id) {
		sqlSession.update(namespace + "logout", id);
	}

	@Override
	public int getNumberOfCurrentUsers() {
		return sqlSession.selectOne(namespace + "getNumberOfCurrentUsers");
	}

	@Override
	public List<MemberVO> getCurrentUsers() {
		return sqlSession.selectList(namespace + "getCurrentUsers");
	}

}
