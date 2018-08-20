package com.jiungkris.jjuproject.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jiungkris.jjuproject.randomchat.MatchingManager;
import com.jiungkris.jjuproject.randomchat.Ticket;

@Controller
public class MatchingController {
	
	@ResponseBody
	@RequestMapping(value = "/isMatched", method = RequestMethod.POST)
	public Map<Object, Object> isMatched(@RequestBody String sessionId) {
		Ticket myTicket = MatchingManager.findMyTicketByStringSession(sessionId);
    	Map<Object, Object> map = new HashMap<Object, Object>();
    	
    	if(myTicket == null) {
    		map.put("isMatched", false);
    	}
    	else {
    		map.put("isMatched", myTicket.isMatched());
    	}
    	
    	return map;
	}
	
	@ResponseBody
	@RequestMapping(value = "/closeThread", method = RequestMethod.POST)
	public void closeThread(@RequestBody String sessionId) {
		Ticket myTicket = MatchingManager.findMyTicketByStringSession(sessionId);
    	myTicket.getThread().interrupt();
	}
}
