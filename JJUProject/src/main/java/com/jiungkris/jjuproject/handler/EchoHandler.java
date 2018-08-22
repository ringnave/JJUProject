package com.jiungkris.jjuproject.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.jiungkris.jjuproject.randomchat.Room;
import com.jiungkris.jjuproject.randomchat.RoomManager;

public class EchoHandler extends TextWebSocketHandler {
	// This sector is public place.
	private static Logger logger = LoggerFactory.getLogger(EchoHandler.class);
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		// Looking for the room of one person.
		Room room = RoomManager.findRoomOfOnePerson();
		
		if(room != null) {
			// If you found the one person room, join it.
			room.join(session);
			logger.info(session.getId() + " joined and matched.");
		}
		else {
			// But If you didn't, Make a room, join in and wait.
			room = RoomManager.makeRoom();
			room.join(session);
			logger.info(session.getId() + " made a room and joined. room is " + room);
		}
	}
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		// Find the room where this session is in.
		Room room = RoomManager.findRoomBySession(session);
		// Call the people list of the room, and then message them.
		for(WebSocketSession wsSession : room.getSessionList()) {
			if(wsSession == session) {
				wsSession.sendMessage(new TextMessage("You: " + message.getPayload() + "\r\n"));
			}
			else {
				wsSession.sendMessage(new TextMessage("Anonymous: " + message.getPayload() + "\r\n"));
			}
		}
		logger.info(session.getId() + " sent a message.");
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		// Find the room where this session is in.
		Room room = RoomManager.findRoomBySession(session);
		
		// Remove the Room
		if(room != null) {
			room.closeSessions();
			RoomManager.removeRoom(room);
		}
		logger.info(session.getId() + " removed the room");
	}
}
