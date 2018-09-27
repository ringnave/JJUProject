package com.jiungkris.jjuproject.message;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.web.socket.WebSocketSession;

public class MessageRoom {
	
	private List<Map<String, WebSocketSession>> sessionList = new LinkedList<Map<String, WebSocketSession>>();
	private String targetId;
	
	public String getTargetId() {
		return targetId;
	}

	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}

	public MessageRoom() {}

	public void join(String id, WebSocketSession session) {
		Map<String, WebSocketSession> map = new HashMap<String, WebSocketSession>();
		map.put(id, session);
		sessionList.add(map);
	}

	public int getNumberOfPeople() {
		return sessionList.size();
	}

	public List<Map<String, WebSocketSession>> getSessionList() {
		return sessionList;
	}

	public void closeSession(String id) {
		for(Map<String, WebSocketSession> map : sessionList) {
			try {
				if(map.get(id) != null) {
					sessionList.remove(map);
					map.get(id).close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public boolean findId(String id) {
		// Prevent index out of bounds exception
		if(sessionList.size() == 0) return false;
		
		if(sessionList.get(0).get(id) != null) {
			return true;
		}
		if(sessionList.size() > 1) {
			if(sessionList.get(1).get(id) != null) return true;
		}
		return false;
	}
}
