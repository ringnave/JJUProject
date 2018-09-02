package com.jiungkris.jjuproject.service;

import java.util.HashMap;
import java.util.List;

import com.jiungkris.jjuproject.vo.MemberVO;

public interface AlarmService {
	public void insertAccountInDB(MemberVO memberVo);
	public void deleteAccountInDB(MemberVO memberVo);
	public void recordAlarm(String myId, String otherId, int count);
	public String readAlarmCount(String myId, String otherId);
	public void removeAlarm(String myId, String otherId);
	public List<HashMap<String, Object>> readForList(String myId);
}
