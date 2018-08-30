package com.jiungkris.jjuproject.service;

import java.util.List;

import com.jiungkris.jjuproject.vo.CommentVO;

public interface CommentService {
	public List<CommentVO> list(int boardNo);
	public void create(CommentVO vo);
	public void delete(int commentNo);
	public List<CommentVO> paging(int boardNo, int offset, int noOfRecords);
	public int getCount(int boardNo);
}
