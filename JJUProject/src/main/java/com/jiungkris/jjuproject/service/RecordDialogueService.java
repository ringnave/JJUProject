package com.jiungkris.jjuproject.service;

import com.jiungkris.jjuproject.vo.MemberVO;

public interface RecordDialogueService {
	public void insertAccountInDB(MemberVO memberVo);
	public void deleteAccountInDB(MemberVO memberVo);
	public void recordDialogue(String myId, String otherId, String message);
	public String readRecord(String myId, String otherId);
	public void removeRecord(String myId, String otherId);
}
