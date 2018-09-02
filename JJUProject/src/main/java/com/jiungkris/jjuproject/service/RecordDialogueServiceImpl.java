package com.jiungkris.jjuproject.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.jiungkris.jjuproject.dao.RecordDialogueDAO;
import com.jiungkris.jjuproject.vo.MemberVO;

@Service
public class RecordDialogueServiceImpl implements RecordDialogueService {

	@Inject
	private RecordDialogueDAO dao;
	
	@Override
	public void insertAccountInDB(MemberVO memberVo) {
		dao.insertAccountInDB(memberVo);
	}

	@Override
	public void deleteAccountInDB(MemberVO memberVo) {
		dao.deleteAccountInDB(memberVo);
	}

	@Override
	public void recordDialogue(String myId, String otherId, String message) {
		dao.recordDialogue(myId, otherId, message);
	}

	@Override
	public String readRecord(String myId, String otherId) {
		return dao.readRecord(myId, otherId);
	}

	@Override
	public void removeRecord(String myId, String otherId) {
		dao.removeRecord(myId, otherId);
	}

}
