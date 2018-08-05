package com.jiungkris.jjuproject.dao;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.jiungkris.jjuproject.vo.MemberVO;

@Repository
public class MemberDAOImpl implements MemberDAO {
	
	String namespace = "com.jiungkris.jjuproject.mappers.memberMapper.";
	
    @Inject
    SqlSession sqlSession;
    
    @Override
    public MemberVO login(MemberVO vo) {
        return sqlSession.selectOne(namespace + "login", vo);
    }

	@Override
	public void join(MemberVO vo) {
		sqlSession.update(namespace + "join", vo);
	}

	@Override
	public boolean idCheck(String id) {
		if(sqlSession.selectOne(namespace + "idCheck", id) != null) {
			return false;
		}
		else {
			return true;
		}
	}

	@Override
	public void deactivate(String id) {
		sqlSession.delete(namespace + "deactivate", id);
	}
}