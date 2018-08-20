package com.jiungkris.jjuproject.randomchat;

import org.springframework.web.socket.WebSocketSession;

public class Ticket {
	private WebSocketSession mySession;
	private WebSocketSession StrangerSession;
	private boolean isMatched = false; 
	private Thread thread;
	
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

	public boolean isMatched() {
		return isMatched;
	}

	public void setMatched(boolean isMatched) {
		this.isMatched = isMatched;
	}

	public Thread getThread() {
		return thread;
	}

	public void setThread(Thread thread) {
		this.thread = thread;
	}
	
}
