package com.koreait.ex07.command;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import com.koreait.ex07.dao.BoardDAO;
import com.koreait.ex07.dto.Board;

public class BoardListCommand {

	@Autowired	
	private BoardDAO boardDAO;	//xml매칭시킬때 class-타입, id-이름 맞추기
	
	public void execute(Model model) {
		List<Board> list = boardDAO.selectBoardList();
		model.addAttribute("list", list);	
	}
}
