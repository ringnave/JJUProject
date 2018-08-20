package com.jiungkris.jjuproject.randomchat;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.springframework.web.socket.WebSocketSession;

public class MatchingManager {
	
	public static List<String> stringSessionList;
	private static List<Ticket> ticketList;
	private static List<WebSocketSession> sessionList;
	
	
	static {
		stringSessionList = new LinkedList<String>();
		ticketList = new LinkedList<Ticket>();
		sessionList = new LinkedList<WebSocketSession>();
	}
	
	public static void collect(Ticket ticket) {
		stringSessionList.add(ticket.getMySession().toString().substring(32, 40).trim());
		ticketList.add(ticket);
		sessionList.add(ticket.getMySession());
	}
	
	public static Ticket findMyTicketBySession(WebSocketSession session) {
		int index = sessionList.indexOf(session);
		if(index == -1) {
			return null;
		}
		else {
			return ticketList.get(index);
		}
	}
	
	public static Ticket findMyTicketByStringSession(String session) {
		int index = stringSessionList.indexOf(session);
		if(index == -1) {
			return null;
		}
		else {
			return ticketList.get(index);
		}
	}
	
	public static void close(WebSocketSession session) {
		stringSessionList.remove(session.toString().substring(32, 40).trim());
		ticketList.remove(findMyTicketBySession(session));
		sessionList.remove(session);
	}
	
	public class ThreradMatching implements Runnable{
		private Ticket myTicket;
		
		public ThreradMatching(Ticket myTicket) {
			this.myTicket = myTicket;
		}
		
		@Override
		public void run() {
			Random random = new Random();
			int strangerNum;
			WebSocketSession strangerSess;
			
			if(myTicket.getStrangerSession() == null) {
				try {
					do{
						if(myTicket.getStrangerSession() != null) return;
						System.out.println(myTicket.getThread() + " : finding..");
						strangerNum = random.nextInt(ticketList.size());
						strangerSess = sessionList.get(strangerNum);
					}while(sessionList.get(strangerNum) == myTicket.getMySession()
							|| ticketList.get(strangerNum).getStrangerSession() != null);
					
					myTicket.setStrangerSession(strangerSess);
					findMyTicketBySession(strangerSess).setStrangerSession(myTicket.getMySession());
					
					myTicket.setMatched(true);
					findMyTicketBySession(strangerSess).setMatched(true);
					
				} catch (Exception e) {
					e.getStackTrace();
				}
			}			
		}
		
	}
}
