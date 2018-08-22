package com.jiungkris.jjuproject.randomchat;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.springframework.web.socket.WebSocketSession;

public class Room {
	private int roomNumber;
	private List<WebSocketSession> sessionList = new LinkedList<WebSocketSession>();
	
	public Room(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	public void join(WebSocketSession session) {
		sessionList.add(session);
	}

	public int getNumberOfPeople() {
		return sessionList.size();
	}

	public Room findSession(WebSocketSession session) {
		for(WebSocketSession ws : sessionList) {
			if(ws == session) {
				return this;
			}
		}
		return null;
	}
	
	public Room findSession(String session) {
		for(WebSocketSession ws : sessionList) {
			if(ws.toString().substring(32, 40).equals(session)) {
				return this;
			}
		}
		return null;
	}	

	public List<WebSocketSession> getSessionList() {
		return sessionList;
	}

	public void closeSessions() {
		for(WebSocketSession ws : sessionList) {
			try {
				ws.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
