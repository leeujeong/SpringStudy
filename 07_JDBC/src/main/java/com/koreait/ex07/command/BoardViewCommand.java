package com.koreait.ex07.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import com.koreait.ex07.dao.BoardDAO;
import com.koreait.ex07.dto.Board;

public class BoardViewCommand {
	
	@Autowired
	private BoardDAO boardDAO;
	
	public void execute(Model model) {
		
		Map<String, Object>map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		Long no = Long.parseLong(request.getParameter("no"));
		
		Board board = boardDAO.selectBoardByNo(no);	//다오에 보내서 결과를 받아옴
		
		model.addAttribute("board", board);	//받아온 결과를 모델에 실어줌.
		
	}
}
