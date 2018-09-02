package com.jiungkris.jjuproject.dao;

import com.jiungkris.jjuproject.vo.MemberVO;

public interface RecordDialogueDAO {
	public void insertAccountInDB(MemberVO memberVo);
	public void deleteAccountInDB(MemberVO memberVo);
	public void recordDialogue(String myId, String otherId, String message);
	public String readRecord(String myId, String otherId);
	public void removeRecord(String myId, String otherId);
}
