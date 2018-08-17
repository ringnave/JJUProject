package com.jiungkris.jjuproject.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.jiungkris.jjuproject.randomchat.MatchingManager;
import com.jiungkris.jjuproject.randomchat.Ticket;
import com.jiungkris.jjuproject.vo.MemberVO;

public class EchoHandler extends TextWebSocketHandler {
	private Logger logger = LoggerFactory.getLogger(EchoHandler.class);
	private Thread t;
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {		

		Ticket myTicket = new Ticket(session);
		MatchingManager.collect(myTicket);

		if(myTicket.getStrangerSession() == null) {
			Runnable r = new MatchingManager().new ThreradMatching(myTicket);
			t = new Thread(r);
			t.start();
		}
		
		logger.info("{} connected", session.getId());
	}
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		Map<String, Object> map = session.getAttributes();
		MemberVO vo = (MemberVO) map.get("loginSuccess");
		
		Ticket myTicket = MatchingManager.findMyTicketBySession(session);
		
		myTicket.getMySession().sendMessage(new TextMessage(vo.getId() + " : " + message.getPayload() + "\r\n"));
		myTicket.getStrangerSession().sendMessage(new TextMessage(vo.getId() + " : " + message.getPayload() + "\r\n"));
		
		logger.info("{} received from {}", message.getPayload(), session.getId());
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		if(t != null) {
			t.interrupt();
		}
		
		MatchingManager.removeTicketAndSession(session);
		
		logger.info("{} disconnected", session.getId());
	}
}
