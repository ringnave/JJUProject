package com.jiungkris.jjuproject.service;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.jiungkris.jjuproject.dao.AlarmDAO;
import com.jiungkris.jjuproject.vo.MemberVO;

@Service
public class AlarmServiceImpl implements AlarmService {

	@Inject
	private AlarmDAO dao;
	
	@Override
	public void insertAccountInDB(MemberVO memberVo) {
		dao.insertAccountInDB(memberVo);
	}

	@Override
	public void deleteAccountInDB(MemberVO memberVo) {
		dao.deleteAccountInDB(memberVo);
	}

	@Override
	public void recordAlarm(String myId, String otherId, int count) {
		dao.recordAlarm(myId, otherId, count);
	}

	@Override
	public String readAlarmCount(String myId, String otherId) {
		return dao.readAlarmCount(myId, otherId);
	}

	@Override
	public void removeAlarm(String myId, String otherId) {
		dao.removeAlarm(myId, otherId);
	}

	@Override
	public List<HashMap<String, Object>> readForList(String myId) {
		return dao.readForList(myId);
	}

}
