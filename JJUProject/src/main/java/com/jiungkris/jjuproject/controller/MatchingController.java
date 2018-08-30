package com.jiungkris.jjuproject.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jiungkris.jjuproject.randomchat.Room;
import com.jiungkris.jjuproject.randomchat.RoomManager;

@Controller
public class MatchingController {
	
	private static Logger logger = LoggerFactory.getLogger(MatchingController.class);
	
	@ResponseBody
	@RequestMapping(value = "/isMatched", method = RequestMethod.POST)
	public Map<Object, Object> isMatched(@RequestBody String sessionId) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		
    	Room room = RoomManager.findRoomBySession(sessionId);
    	
		logger.info(sessionId + "'room: " + room);
		
    	if(room != null && room.getNumberOfPeople() == 2) {
			map.put("isMatched", true);
			logger.info(sessionId + " is matched (true)");
		}
		else {
			map.put("isMatched", false);
			logger.info(sessionId + " is not matched (false)");
		}
    	
    	return map;
	}
	
	@ResponseBody
	@RequestMapping(value = "/enrollSession", method = RequestMethod.POST)
	public void enrollSession(@RequestBody String otherId, HttpSession session) {
		session.setAttribute("otherId", otherId);
	}
}
