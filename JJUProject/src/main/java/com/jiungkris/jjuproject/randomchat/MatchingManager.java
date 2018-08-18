package com.jiungkris.jjuproject.randomchat;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.web.socket.WebSocketSession;

public class MatchingManager {
	
	private static List<Ticket> ticketList;
	private static List<WebSocketSession> sessionList;
	
	
	static {
		 ticketList = new ArrayList<Ticket>();
		 sessionList = new ArrayList<WebSocketSession>();
	}
	
	public static void collect(Ticket ticket) {
		ticketList.add(ticket);
		sessionList.add(ticket.getMySession());
	}
	
	public static Ticket findMyTicketBySession(WebSocketSession session) {
		return ticketList.get(sessionList.indexOf(session));
	}
	
	public static void removeTicketAndSession(WebSocketSession session) {
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
				do{
					strangerNum = random.nextInt(ticketList.size());
					strangerSess = sessionList.get(strangerNum);				
				}while(sessionList.get(strangerNum) == myTicket.getMySession()
						|| ticketList.get(strangerNum).getStrangerSession() != null);
				System.out.println("ticketList: " + ticketList);
				System.out.println(myTicket.getMySession() + " matched with " + strangerSess);
				
				myTicket.setStrangerSession(strangerSess);
				MatchingManager.findMyTicketBySession(strangerSess).setStrangerSession(myTicket.getMySession());
			}
			else {
				System.out.println(myTicket.getMySession() + " matched with " + myTicket.getStrangerSession());
			}
			
		}
		
	}
}
