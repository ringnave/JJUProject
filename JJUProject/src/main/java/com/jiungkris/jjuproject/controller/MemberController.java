package com.jiungkris.jjuproject.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jiungkris.jjuproject.service.MemberService;
import com.jiungkris.jjuproject.vo.MemberVO;

@Controller
@RequestMapping(value = "/member")
public class MemberController {
 
    @Inject
    MemberService service;
 
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginForm(){
        return "member/loginForm";
    }
     
    @RequestMapping(value = "/loginProcess", method = RequestMethod.POST)
    public String loginProcess(HttpSession session, MemberVO dto) {
        String page = "";
        
        if ( session.getAttribute("loginSuccess") != null ) {
            session.removeAttribute("loginSuccess");
        }
        MemberVO vo = service.login(dto);
        if ( vo != null ){
            session.setAttribute("loginSuccess", vo);
            page = "redirect:/";
        }else {
        	page = "redirect:/login";
        }
         
        return page;
    }
 
    @RequestMapping(value = "/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
    
    @RequestMapping(value = "/join")
    public String join() {
    	return "member/joinForm";
    }
    
    @RequestMapping(value = "/joinProcess", method = RequestMethod.POST)
    public String joinProcess(MemberVO dto) {
    	
    	String page = "";
    	
    	service.join(dto);
		page = "redirect:/";
    	
    	return page;
    }
}