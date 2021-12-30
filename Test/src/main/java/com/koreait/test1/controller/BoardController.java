package com.koreait.test1.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.koreait.test1.service.DeleteBoardService;
import com.koreait.test1.service.InsertBoardService;
import com.koreait.test1.service.SelectBoardListService;
import com.koreait.test1.service.SelectBoardViewService;
import com.koreait.test1.service.UpdateBoardService;

@Controller
public class BoardController {

	private SqlSession sqlSession;
	private SelectBoardListService listService;
	private SelectBoardViewService viewService;
	private InsertBoardService insertService;
	private UpdateBoardService updateService;
	private DeleteBoardService deleteService;

	public BoardController(SqlSession sqlSession, SelectBoardListService listService,
			SelectBoardViewService viewService, InsertBoardService insertService, UpdateBoardService updateService,
			DeleteBoardService deleteService) {
		super();
		this.sqlSession = sqlSession;
		this.listService = listService;
		this.viewService = viewService;
		this.insertService = insertService;
		this.updateService = updateService;
		this.deleteService = deleteService;
	}

	@GetMapping(value = {"/", "selectBoardList.do"})
	public String list(Model model) {
		listService.execute(sqlSession, model);
		return "board/list";
	}

	@GetMapping(value = "insertPage.do")
	public String insertPage() {
		return "board/insert";
	}

	@GetMapping(value = "insert.do")
	public void insert(HttpServletRequest request, HttpServletResponse response, Model model) {
		model.addAttribute("request", request);
		model.addAttribute("response", response);
		insertService.execute(sqlSession, model);
	}

	@GetMapping(value = "selectBoardByIdx.do")
	public String view(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		viewService.execute(sqlSession, model);
		return "board/view";
	}

	@GetMapping(value = "updateBoard.do")
	public void update(HttpServletRequest request, HttpServletResponse response, Model model) {
		model.addAttribute("request", request);
		model.addAttribute("response", response);
		updateService.execute(sqlSession, model);
	}

	@GetMapping(value = "deleteBoard.do")
	public void delete(HttpServletRequest request, HttpServletResponse response, Model model) {
		model.addAttribute("request", request);
		model.addAttribute("response", response);
		deleteService.execute(sqlSession, model);
	}

}
