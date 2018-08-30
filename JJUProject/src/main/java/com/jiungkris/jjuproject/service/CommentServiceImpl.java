package com.jiungkris.jjuproject.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.jiungkris.jjuproject.dao.CommentDAO;
import com.jiungkris.jjuproject.vo.CommentVO;

@Service
public class CommentServiceImpl implements CommentService {

	@Inject
	CommentDAO commentDao;
	
	@Override
	public List<CommentVO> list(int boardNo) {
		return commentDao.list(boardNo);
	}

	@Override
	public void create(CommentVO vo) {
		commentDao.create(vo);
	}

	@Override
	public void delete(int commentNo) {
		commentDao.delete(commentNo);
	}

	@Override
	public List<CommentVO> paging(int boardNo, int offset, int noOfRecords) {
		return commentDao.paging(boardNo, offset, noOfRecords);
	}

	@Override
	public int getCount(int boardNo) {
		return commentDao.getCount(boardNo);
	}

}
