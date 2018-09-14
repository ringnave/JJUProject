package com.jiungkris.jjuproject.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jiungkris.jjuproject.service.CurrentService;
import com.jiungkris.jjuproject.vo.MemberVO;

@Controller
public class ChatController {
	
	@Inject
	CurrentService currentService;
	
	@RequestMapping(value = "/chat", method = RequestMethod.GET)
	public String chat(Model model, HttpSession session) {
		//For sideIds.jsp
		List<MemberVO> idList = new ArrayList<MemberVO>();
		idList = currentService.getCurrentUsers();
		model.addAttribute("idList", idList);
		
		return "/chat";
	}
}
