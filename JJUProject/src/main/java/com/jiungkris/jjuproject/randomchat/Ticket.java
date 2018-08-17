package com.jiungkris.jjuproject.randomchat;

import org.springframework.web.socket.WebSocketSession;

public class Ticket {
	private WebSocketSession mySession;
	private WebSocketSession StrangerSession;
	
	public Ticket(WebSocketSession mySession) {
		this.mySession = mySession;
	}

	public WebSocketSession getMySession() {
		return mySession;
	}

	public void setMySession(WebSocketSession mySession) {
		this.mySession = mySession;
	}

	public WebSocketSession getStrangerSession() {
		return StrangerSession;
	}

	public void setStrangerSession(WebSocketSession strangerSession) {
		this.StrangerSession = strangerSession;
	}
}
