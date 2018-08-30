package com.jiungkris.jjuproject.dao;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.jiungkris.jjuproject.vo.CommentVO;

@Repository
public class CommentDAOImpl implements CommentDAO {

	@Inject
	SqlSession sqlSession;
	
	private String namespace = "com.jiungkris.jjuproject.mappers.commentMapper.";
	
	@Override
	public List<CommentVO> list(int boardNo) {
		return sqlSession.selectList(namespace + "list", boardNo);
	}

	@Override
	public void create(CommentVO vo) {
		sqlSession.insert(namespace + "insert", vo);		
	}

	@Override
	public void delete(int commentNo) {
		sqlSession.delete(namespace + "delete", commentNo);
	}

	@Override
	public List<CommentVO> paging(int boardNo, int offset, int noOfRecords) {
		List<CommentVO> paging = new LinkedList<CommentVO>();
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("boardNo", boardNo);
		params.put("offset", offset);
		params.put("noOfRecords", noOfRecords);
		paging = sqlSession.selectList(namespace + "paging", params);
		return paging;
	}

	@Override
	public int getCount(int boardNo) {
		return sqlSession.selectOne(namespace + "getCount", boardNo);
	}

}
