package com.koreait.test1.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.koreait.test1.dao.BoardDAO;

public class SelectBoardListService implements BoardService {

	@Override
	public void execute(SqlSession sqlSession, Model model) {
		
		BoardDAO boardDAO = sqlSession.getMapper(BoardDAO.class);
		model.addAttribute("list", boardDAO.selectBoardList());
		model.addAttribute("totalRecord", boardDAO.selectBoardCount());
		
	}

}
