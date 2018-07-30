package com.jiungkris.jjuproject.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jiungkris.jjuproject.paging.Paging;
import com.jiungkris.jjuproject.service.BBSService;
import com.jiungkris.jjuproject.vo.BBSVO;
import com.jiungkris.jjuproject.vo.MemberVO;

@Controller
@RequestMapping(value = "/BBS")
public class BBSController {
	
	@Inject
	BBSService service;	
	
	@RequestMapping(value="/list")
	public String list(Model model, HttpServletRequest req) throws Exception {
		int currentPageNo = 1;
		int maxPost = 10;
		
		if(req.getParameter("page") != null)
			currentPageNo = Integer.parseInt(req.getParameter("page"));
		
		Paging paging = new Paging(currentPageNo, maxPost);
		
		int offset = (paging.getCurrentPageNo() - 1) * paging.getMaxPost();
		
		List<BBSVO> page = new ArrayList<BBSVO>();
		page = service.paging(offset, paging.getMaxPost());
		paging.setNumberOfRecords(service.getCount());

		paging.makePaging();
		
		List<MemberVO> idList = new ArrayList<MemberVO>();
		
		idList = service.getIds();
		
		model.addAttribute("page", page);
		model.addAttribute("paging", paging);
		model.addAttribute("idList", idList);

		return "/BBS/list";
	}
	
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public String read(HttpServletRequest request, Model model) {
		int b_no = Integer.parseInt(request.getParameter("b_no"));
		BBSVO dto = new BBSVO();
		
		try {
			dto = service.read(b_no);
			dto.setB_content(dto.getB_content().replace("\r\n", "<br>"));
			dto.setB_views(dto.getB_views()+1);
			service.update(dto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("read", dto);
		
		return "/BBS/read";
	}
	
	@RequestMapping(value = "/create")
	public String create() {
		return "/BBS/createForm";
	}
	
	@RequestMapping(value = "/createProcess", method = RequestMethod.POST)
	public String createProcess(BBSVO dto) {
		
		try {
			service.create(dto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:list";
	}
	
	@RequestMapping(value = "/prePasswordCheck")
	public String prePasswordCheck(HttpServletRequest request, Model model) {
		model.addAttribute("b_no", Integer.parseInt(request.getParameter("b_no")));
		model.addAttribute("b_type", request.getParameter("b_type"));
		return "/BBS/passwordCheck";
	}
	
	@RequestMapping(value = "/passwordProcess", method = RequestMethod.POST)
	public String passwordProcess(BBSVO dto, Model model, HttpServletRequest request) {

		String page = "";
		String realPW = "";
		String route = request.getParameter("b_type");
		
		try {
			realPW = dto.getB_pw();
			dto = service.read(dto.getB_no());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(realPW.equals(dto.getB_pw())) {
			switch (route) {
			case "update":
				dto.setB_content(dto.getB_content().replace("<br>", "\r\n"));
				model.addAttribute("dto", dto);
				page = "/BBS/updateForm";
				break;
			case "delete":
				try {
					service.delete(dto.getB_no());
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
			service.update(dto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:list";
	}
}
