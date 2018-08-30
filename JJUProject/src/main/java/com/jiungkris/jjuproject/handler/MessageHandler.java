package com.jiungkris.jjuproject.handler;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.jiungkris.jjuproject.message.MessageRoom;
import com.jiungkris.jjuproject.message.MessageRoomManager;
import com.jiungkris.jjuproject.vo.MemberVO;

public class MessageHandler extends TextWebSocketHandler {
	// This sector is public place.
	private static Logger logger = LoggerFactory.getLogger(MessageHandler.class);
		
	@Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		Map<String,Object> map = session.getAttributes();
		String otherId = (String)map.get("otherId");
		MemberVO myVo = (MemberVO) map.get("loginSuccess");
		
		MessageRoom room = MessageRoomManager.findOnePersonRoomById(otherId);
		
		if(room != null) {
			// If you found the one person room, join it.
			room.join(myVo.getId(), session);
			logger.info(session.getId() + " joined and matched.");
		}
		else {
			// But If you didn't, Make a room, join in and wait.
			room = MessageRoomManager.makeRoom();
			room.join(myVo.getId(), session);
			logger.info(session.getId() + " made a room and joined. room is " + room);
		}
    }
	
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
    	Map<String,Object> map = session.getAttributes();
    	String otherId = (String)map.get("otherId");
		MemberVO myVo = (MemberVO) map.get("loginSuccess");
    	
    	// Find the room where this session is in.
		MessageRoom room = MessageRoomManager.findRoomById(myVo.getId());
		// Call the people list of the room, and then message them.
		for(Map<String, WebSocketSession> map2 : room.getSessionList()) {
			if(map2.get(myVo.getId()) == session) {
				map2.get(myVo.getId()).sendMessage(new TextMessage("You: " + message.getPayload() + "\r\n"));
			}
			else {
				map2.get(otherId).sendMessage(new TextMessage(otherId + ": " + message.getPayload() + "\r\n"));
			}
		}
		logger.info(session.getId() + " sent a message.");
    }
    
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
    	Map<String,Object> map = session.getAttributes();
    	String otherId = (String)map.get("otherId");
		MemberVO myVo = (MemberVO) map.get("loginSuccess");
		
		// Find the room where this session is in.
		MessageRoom room = MessageRoomManager.findRoomById(myVo.getId());
		
		// Remove the Room
		if(room != null) {
			room.closeSession(myVo.getId());
			room.closeSession(otherId);
			MessageRoomManager.removeRoom(room);
		}
		logger.info(session.getId() + " removed the room");
    }
}
