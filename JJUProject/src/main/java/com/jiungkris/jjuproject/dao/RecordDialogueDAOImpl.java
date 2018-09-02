package com.jiungkris.jjuproject.dao;

import java.util.HashMap;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.jiungkris.jjuproject.vo.MemberVO;

@Repository
public class RecordDialogueDAOImpl implements RecordDialogueDAO {
	
	@Inject
	private SqlSession sqlSession;
	
	private String namespace = "com.jiungkris.jjuproject.mappers.recordsDialogueMapper.";
	
	@Override
	public void insertAccountInDB(MemberVO memberVo) {
		sqlSession.insert(namespace + "insertRow", memberVo);
		sqlSession.insert(namespace + "insertCol", memberVo);
	}
	
	@Override
	public void deleteAccountInDB(MemberVO memberVo) {
		sqlSession.delete(namespace + "deleteRow", memberVo);
		sqlSession.delete(namespace + "deleteCol", memberVo);
	}

	@Override
	public void recordDialogue(String myId, String otherId, String message) {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("myId", myId);
		params.put("otherId", otherId);
		params.put("message", message);
		sqlSession.insert(namespace + "recordDialogue", params);
	}

	@Override
	public String readRecord(String myId, String otherId) {
		HashMap<String, Object> params = new HashMap<String, Object>();
		String record = null;
		params.put("myId", myId);
		params.put("otherId", otherId);
		record = sqlSession.selectOne(namespace + "readRecord", params);
		return record;
	}

	@Override
	public void removeRecord(String myId, String otherId) {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("myId", myId);
		params.put("otherId", otherId);
		sqlSession.delete(namespace + "removeRecord", params);		
	}
}
