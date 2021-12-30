package com.koreait.test1.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.koreait.test1.dao.BoardDAO;

public class SelectBoardViewService implements BoardService {

	@Override
	public void execute(SqlSession sqlSession, Model model) {
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		int idx = Integer.parseInt(request.getParameter("idx"));
		
		BoardDAO boardDAO = sqlSession.getMapper(BoardDAO.class);
		
		model.addAttribute("board", boardDAO.selectByIdx(idx));
		
	}

}
