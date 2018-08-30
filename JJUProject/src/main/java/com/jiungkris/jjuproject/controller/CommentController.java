package com.jiungkris.jjuproject.controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jiungkris.jjuproject.paging.Paging;
import com.jiungkris.jjuproject.service.CommentService;
import com.jiungkris.jjuproject.vo.CommentVO;
import com.jiungkris.jjuproject.vo.MemberVO;

@RestController
@RequestMapping("/comment")
public class CommentController {
	
	@Inject
	CommentService commentService;
	
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public void insert(@ModelAttribute CommentVO commentVo, HttpSession session) {
		MemberVO memberVo = (MemberVO) session.getAttribute("loginSuccess");
		commentVo.setId(memberVo.getId());
		commentVo.setName(memberVo.getName());
		commentService.create(commentVo);
	}

//  JUST FOR BACKUP	
//	@RequestMapping(value = "/list", method = RequestMethod.GET)
//	public List<CommentVO> list(HttpServletRequest request, Model model) {
//		int boardNo = Integer.parseInt(request.getParameter("b_no"));
//		return commentService.list(boardNo);
//	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public Map<Object, Object> list(HttpServletRequest request, Model model) {
		int boardNo = Integer.parseInt(request.getParameter("b_no"));
		Map<Object, Object> map = new HashMap<Object, Object>();
		
		int currentPageNo = 1;
		int maxComment = 3;
		
		if(request.getParameter("page") != null)
			currentPageNo = Integer.parseInt(request.getParameter("page"));
		
		Paging paging = new Paging(currentPageNo, maxComment);
		
		int offset = (paging.getCurrentPageNo() - 1) * paging.getMaxPost();
		
		List<CommentVO> comments = new LinkedList<CommentVO>();
		comments = commentService.paging(boardNo, offset, paging.getMaxPost());
		paging.setNumberOfRecords(commentService.getCount(boardNo));
		
		paging.makePaging();
		
		map.put("comments", comments);
		map.put("paging", paging);
		
		return map;
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public void delete(HttpServletRequest request, Model model) {
		int commentNo = Integer.parseInt(request.getParameter("c_no"));
		commentService.delete(commentNo);
	}
	
	@RequestMapping(value = "/getSession", method = RequestMethod.POST)
    @ResponseBody
    public Map<Object, Object> getSession(HttpSession session) {
    	Map<Object, Object> map = new HashMap<Object, Object>();
    	MemberVO memberVo = null;
    	
    	try {
    		memberVo = (MemberVO) session.getAttribute("loginSuccess");
    		map.put("id", memberVo.getId());
		} catch (Exception e) {
			e.getStackTrace();
		}
    	
    	return map;
    }
}
