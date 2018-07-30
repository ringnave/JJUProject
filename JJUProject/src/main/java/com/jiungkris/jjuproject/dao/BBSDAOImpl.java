package com.jiungkris.jjuproject.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.jiungkris.jjuproject.vo.BBSVO;
import com.jiungkris.jjuproject.vo.MemberVO;

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

	@Override
	public List<BBSVO> paging(int offset, int noOfRecords) throws Exception {
		List<BBSVO> paging = new ArrayList<BBSVO>();
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		
		params.put("offset", offset);
		params.put("noOfRecords", noOfRecords);
		
		paging = sqlSession.selectList("paging", params);
		
		return paging;
	}

	@Override
	public int getCount() throws Exception {
		return sqlSession.selectOne("getCount");
	}

	@Override
	public List<MemberVO> getIds() throws Exception {
		return sqlSession.selectList(namespace + "getIds");
	}

}
