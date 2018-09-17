package com.jiungkris.jjuproject.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jiungkris.jjuproject.paging.Paging;
import com.jiungkris.jjuproject.service.BBSService;
import com.jiungkris.jjuproject.service.CurrentService;
import com.jiungkris.jjuproject.vo.BBSVO;
import com.jiungkris.jjuproject.vo.MemberVO;

@Controller
@RequestMapping(value = "/BBS")
public class BBSController {
	private static Logger logger = LoggerFactory.getLogger(BBSController.class);
	
	@Inject
	BBSService bbsService;	
	
	@Inject
	CurrentService currentService;
	
	@RequestMapping(value="/list")
	public String list(Model model, HttpServletRequest req) throws Exception {
		//For sideIds.jsp
		List<MemberVO> idList = new ArrayList<MemberVO>();
		idList = currentService.getCurrentUsers();
		model.addAttribute("idList", idList);
		
		int currentPageNo = 1;
		int maxPost = 10;
		
		if(req.getParameter("page") != null)
			currentPageNo = Integer.parseInt(req.getParameter("page"));
		
		Paging paging = new Paging(currentPageNo, maxPost);
		
		int offset = (paging.getCurrentPageNo() - 1) * paging.getMaxPost();
		
		List<BBSVO> pages = new ArrayList<BBSVO>();
		pages = bbsService.paging(offset, paging.getMaxPost());
		paging.setNumberOfRecords(bbsService.getCount());

		paging.makePaging();

		model.addAttribute("pages", pages);
		model.addAttribute("paging", paging);

		
		return "/BBS/list";
	}
	
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public String read(HttpServletRequest request, Model model) {
		//For sideIds.jsp
		List<MemberVO> idList = new ArrayList<MemberVO>();
		idList = currentService.getCurrentUsers();
		model.addAttribute("idList", idList);
		
		int b_no = Integer.parseInt(request.getParameter("b_no"));
		BBSVO dto = new BBSVO();
		
		try {
			dto = bbsService.read(b_no);
			dto.setB_content(dto.getB_content().replace("\r\n", "<br>"));
			dto.setB_views(dto.getB_views()+1);
			bbsService.update(dto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("read", dto);
		
		return "/BBS/read";
	}
	
	@RequestMapping(value = "/create")
	public String create(Model model) {
		//For sideIds.jsp
		List<MemberVO> idList = new ArrayList<MemberVO>();
		idList = currentService.getCurrentUsers();
		model.addAttribute("idList", idList);
		
		return "/BBS/createForm";
	}
	
	@RequestMapping(value = "/createProcess", method = RequestMethod.POST)
	public String createProcess(BBSVO dto, HttpSession session) {
		if(session.getAttribute("loginSuccess") == null) {
			dto.setB_writer(dto.getB_writer() + " (No Account)");
		}
		
		try {
			bbsService.create(dto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:list";
	}
	
	@RequestMapping(value = "/prePasswordCheck")
	public String prePasswordCheck(HttpServletRequest request, Model model) {
		//For sideIds.jsp
		List<MemberVO> idList = new ArrayList<MemberVO>();
		idList = currentService.getCurrentUsers();
		model.addAttribute("idList", idList);
		
		model.addAttribute("b_no", Integer.parseInt(request.getParameter("b_no")));
		model.addAttribute("b_type", request.getParameter("b_type"));
		return "/BBS/passwordCheck";
	}
	
	@RequestMapping(value = "/passwordProcess", method = RequestMethod.POST)
	public String passwordProcess(BBSVO dto, Model model, HttpServletRequest request) {
		//For sideIds.jsp
		List<MemberVO> idList = new ArrayList<MemberVO>();
		idList = currentService.getCurrentUsers();
		model.addAttribute("idList", idList);
		
		String page = "";
		String realPW = "";
		String route = request.getParameter("b_type");
		
		try {
			realPW = dto.getB_pw();
			dto = bbsService.read(dto.getB_no());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(realPW.equals(dto.getB_pw())) {
			switch (route) {
				case "update":
					dto.setB_content(dto.getB_content().replace("<br>", "\r\n"));

					// Removing (No Account)
					String writer = dto.getB_writer();
					dto.setB_writer(writer.substring(0, writer.length()-13));
					
					model.addAttribute("dto", dto);
					page = "/BBS/updateForm";
					break;
				case "delete":
					try {
						bbsService.delete(dto.getB_no());
					} catch (Exception e) {
						e.printStackTrace();
					}
					page = "redirect:list";
				default:
					break;
			}
			
		}
		else {
			page = "/BBS/passwordFail";
		}
		
		return page;
	}
	
	@RequestMapping(value = "/updateProcess", method = RequestMethod.POST)
	public String updateProcess(BBSVO dto) {
		try {
			dto.setB_writer(dto.getB_writer() + " (No Account)");
			bbsService.update(dto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:list";
	}
	
	@RequestMapping(value = "/pass", method = RequestMethod.GET)
	public String pass(Model model, HttpServletRequest request) {
		String page = "";
		String route = request.getParameter("b_type");
		int no = Integer.parseInt(request.getParameter("b_no"));
		BBSVO dto = new BBSVO();
		
		try {
			dto = bbsService.read(no);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		switch (route) {
			case "update":
				dto.setB_content(dto.getB_content().replace("<br>", "\r\n"));
				model.addAttribute("dto", dto);
				page = "/BBS/updateForm";
				break;
			case "delete":
				try {
					bbsService.delete(dto.getB_no());
				} catch (Exception e) {
					e.printStackTrace();
				}
				page = "redirect:list";
			default:
				break;
		}
		
		return page;
	}
}
