package com.koreait.ex07.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.koreait.ex07.command.BoardDeleteCommand;
import com.koreait.ex07.command.BoardInsertCommand;
import com.koreait.ex07.command.BoardListCommand;
import com.koreait.ex07.command.BoardUpdateCommand;
import com.koreait.ex07.command.BoardViewCommand;

@Controller
@RequestMapping("board")
public class BoardController {

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);   //this는 현재 클래스

//	
//	private BoardListCommand BoardListCommand;
//	private BoardInsertCommand BoardInsertCommand;
//	private BoardViewCommand BoardViewCommand;
//	private BoardUpdateCommand BoardUpdateCommand;
//	private BoardDeleteCommand BoardDeleteCommand;
//	
//	public BoardController(BoardListCommand boardListCommand,
//			BoardInsertCommand boardInsertCommand,
//			BoardViewCommand boardViewCommand,
//			BoardUpdateCommand boardUpdateCommand,
//			BoardDeleteCommand boardDeleteCommand) {
//		super();
//		this.BoardListCommand = boardListCommand;
//		this.BoardInsertCommand = boardInsertCommand;
//		this.BoardViewCommand = boardViewCommand;
//		this.BoardUpdateCommand = boardUpdateCommand;
//		this.BoardDeleteCommand = boardDeleteCommand;
//	}

	@Autowired
	private BoardListCommand BoardListCommand;
	@Autowired
	private BoardInsertCommand BoardInsertCommand;
	@Autowired
	private BoardViewCommand BoardViewCommand;
	@Autowired
	private BoardUpdateCommand BoardUpdateCommand;
	@Autowired
	private BoardDeleteCommand BoardDeleteCommand;
	
	

	@GetMapping(value="selectBoardList.do")
	public String selectBoardList(Model model) {	//모델에 저장해두고 포워드하면 jsp에서 볼수있음
		BoardListCommand.execute(model);
		return "board/list";
	}
	
	@GetMapping(value="insertBoardForm.do")
	public String insertBoardForm() {
		return "board/insert";
	}
	
	@PostMapping(value="insertBoard.do")
	public void insertBoard(HttpServletRequest request, HttpServletResponse response, Model model) {
		//만들고 있는 command의 형태를 통일시키기 위해 model작성
		//BoardInsertCommand는 Model만 받을 수 있어서 model에 request, response담아줌
		//BoardInsertCommand는 받은 Model에서  request, response 꺼내서 사용함
		//JSP에 사용하고자 하는게 아니라 Command에서 사용하는 model
		model.addAttribute("request", request);
		model.addAttribute("response", response);	
		BoardInsertCommand.execute(model);
	}
	
	@GetMapping(value="selectBoardByNo.do")
	public String selectBoardByNo(HttpServletRequest request, Model model) {	//응답은 안만들어도됨
		model.addAttribute("request", request);
		BoardViewCommand.execute(model);
		return "board/detail";
	}
	@GetMapping(value="updateBoardForm.do")
	public String updateBoardForm() {
		return "board/update";
	}
	
	@GetMapping(value="updateBoard.do")
	public void updateBoard(HttpServletRequest request, HttpServletResponse response, Model model) {
		model.addAttribute("request", request);
		model.addAttribute("response", response);
		BoardUpdateCommand.excute(model);
	}
	@GetMapping(value="deleteBoard.do")
	public void deleteBoard(HttpServletRequest request, HttpServletResponse response, Model model) {
		model.addAttribute("request", request);
		model.addAttribute("response", response);
		BoardDeleteCommand.execute(model);
	}
	
	
	
	
	
}
