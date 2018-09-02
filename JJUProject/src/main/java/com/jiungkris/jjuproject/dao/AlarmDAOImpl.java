package com.jiungkris.jjuproject.dao;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.jiungkris.jjuproject.vo.MemberVO;

@Repository
public class AlarmDAOImpl implements AlarmDAO {
	
	@Inject
	private SqlSession sqlSession;
	private String namespace = "com.jiungkris.jjuproject.mappers.alarmMapper.";

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
	public void recordAlarm(String myId, String otherId, int count) {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("myId", myId);
		params.put("otherId", otherId);
		params.put("count", count);
		sqlSession.insert(namespace + "recordAlarm", params);
	}

	@Override
	public String readAlarmCount(String myId, String otherId) {
		HashMap<String, Object> params = new HashMap<String, Object>();
		String count = null;
		params.put("myId", myId);
		params.put("otherId", otherId);
		count = sqlSession.selectOne(namespace + "readAlarmCount", params);
		return count;
	}

	@Override
	public void removeAlarm(String myId, String otherId) {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("myId", myId);
		params.put("otherId", otherId);
		sqlSession.delete(namespace + "removeAlarm", params);
	}

	@Override
	public List<HashMap<String, Object>> readForList(String myId) {
		return sqlSession.selectList(namespace + "readForList", myId);
	}

}
