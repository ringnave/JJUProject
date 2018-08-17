package com.jiungkris.jjuproject.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jiungkris.jjuproject.service.CurrentService;
import com.jiungkris.jjuproject.vo.MemberVO;

@Controller
@RequestMapping(value = "/current")
public class CurrentController {
	
	@Inject
	private CurrentService currentService;
	
    @ResponseBody
	@RequestMapping(value = "/getCurrentUsers", method = RequestMethod.GET)
    public Map<Object, Object> getCurrentUsers() {
    	Map<Object, Object> map = new HashMap<Object, Object>();
    	    	
    	int currentUsers = 0;
    	List<MemberVO> vo = null;
    	
    	try {
    		currentUsers = currentService.getNumberOfCurrentUsers();
        	vo = currentService.getCurrentUsers();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	
    	map.put("currentUsersList", vo);
        map.put("currentUsers", currentUsers);
    	
    	return map;
    }
}