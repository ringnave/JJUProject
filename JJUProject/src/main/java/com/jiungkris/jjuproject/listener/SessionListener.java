package com.jiungkris.jjuproject.listener;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.jiungkris.jjuproject.service.CurrentService;
import com.jiungkris.jjuproject.vo.MemberVO;

public class SessionListener implements HttpSessionListener{
	
	@Inject
	private CurrentService currentService;
	
	@Override
	public void sessionCreated(HttpSessionEvent event) {
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		HttpSession session = event.getSession();
		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(session.getServletContext());
		currentService = (CurrentService) context.getBean("currentService");
		
		MemberVO vo = (MemberVO) event.getSession().getAttribute("loginSuccess");
		
		if(vo != null) {
			currentService.logout(vo.getId());
		}
	}

}
