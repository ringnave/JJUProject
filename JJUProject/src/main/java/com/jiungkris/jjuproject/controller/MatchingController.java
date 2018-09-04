package com.jiungkris.jjuproject.controller;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jiungkris.jjuproject.message.MessageRoom;
import com.jiungkris.jjuproject.message.MessageRoomManager;
import com.jiungkris.jjuproject.randomchat.Room;
import com.jiungkris.jjuproject.randomchat.RoomManager;
import com.jiungkris.jjuproject.service.AlarmService;
import com.jiungkris.jjuproject.service.RecordDialogueService;
import com.jiungkris.jjuproject.vo.MemberVO;

@Controller
public class MatchingController {
	
	private static Logger logger = LoggerFactory.getLogger(MatchingController.class);
	
	@Inject
	private RecordDialogueService recordDialogueService;
	
	@Inject
	private AlarmService alarmService;
	
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
	
	@ResponseBody
	@RequestMapping(value = "/readRecord", method = RequestMethod.POST, produces = "application/text; charset=utf8")
	public String readRecord(HttpSession session) {
		MemberVO myVo = (MemberVO) session.getAttribute("loginSuccess");
		String otherId = (String) session.getAttribute("otherId");
		return recordDialogueService.readRecord(myVo.getId(), otherId);
	}
	
	@ResponseBody
	@RequestMapping(value = "/removeRecord", method = RequestMethod.POST)
	public void removeRecord(HttpSession session) {
		MemberVO myVo = (MemberVO) session.getAttribute("loginSuccess");
		String otherId = (String) session.getAttribute("otherId");
		recordDialogueService.removeRecord(myVo.getId(), otherId);
	}
	
	@ResponseBody
	@RequestMapping(value = "/getAlarm", method = RequestMethod.POST)
	public Map<Object, Object> getAlarm(@RequestBody String otherId, HttpSession session) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		MemberVO myVo = (MemberVO) session.getAttribute("loginSuccess");
		String count = null;
		
		try {
			count = (String) alarmService.readForList(myVo.getId()).get(0).get(otherId);
		} catch (Exception e) {
			e.getStackTrace();
		}
		
		map.put("count", count);
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value = "/removeAlarm", method = RequestMethod.POST)
	public void removeAlarm(HttpSession session) {
		MemberVO myVo = (MemberVO) session.getAttribute("loginSuccess");
		String otherId = (String) session.getAttribute("otherId");
		alarmService.removeAlarm(myVo.getId(), otherId);
	}
}
