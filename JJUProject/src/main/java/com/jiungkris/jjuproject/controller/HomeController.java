package com.jiungkris.jjuproject.controller;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
 
/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
    
    private static Logger logger = LoggerFactory.getLogger(HomeController.class);
    
    /**
     * Simply selects the home view to render by returning its name.
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Locale locale, Model model) throws Exception{
        logger.info("home");
 
        return "redirect:BBS/list";
    }
    
    @ResponseBody
	@RequestMapping(value = "/sessionCheck", method = RequestMethod.POST)
	public Map<Object, Object> sessionCheck(HttpSession session) {
		boolean isSignedIn = false;
    	
    	Map<Object, Object> map = new HashMap<Object, Object>();
    	
    	if(session.getAttribute("loginSuccess") != null) {
    		isSignedIn = true;
    	}
    	else {
    		isSignedIn = false;
    	}
    	
        map.put("isSignedIn", isSignedIn);
    	
    	return map;
	}
}