package com.koreait.integration1.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.koreait.integration1.domain.SearchBoard;
import com.koreait.integration1.service.SearchService;

@Controller
public class SearchBoardController {

	@Autowired
	private SearchService service;
	
	@GetMapping(value="/")
	public String index() {
		return "index";
	}
	
	@GetMapping(value="search/findAllBoard", produces="application/json; charset=UTF-8")
	@ResponseBody
	public Map<String, Object> findAllBoard(){
		List<SearchBoard> list = service.findAllBoard();
		
		Map<String , Object> map = new HashMap<String, Object>();
		if(list.size() == 0) {
			map.put("status", 500);
			map.put("message", "저장된 목록이 없습니다.");
			map.put("list", null);
		}else {
			map.put("status", 200);
			map.put("message", "전체 " + list.size() + "개의 목록을 가져왔습니다.");
			map.put("list", list);
		}
		return map;
	}
	
	@GetMapping(value="search/findBoard" , produces="application/json; charset=UTF-8")
	@ResponseBody
	public Map<String, Object> findBoard(HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("column", request.getParameter("column"));
		map.put("query", request.getParameter("query"));
		
		List<SearchBoard> list = service.findBoard(map);
		Map<String , Object> m = new HashMap<String, Object>();
		if(list.size() == 0) {
			m.put("status", 500);
			m.put("message", "결과가 없습니다.");
			m.put("list", null);
		}else {
			m.put("status", 200);
			m.put("message", "전체 " + list.size() + "개의 목록이 검색되었습니다.");
			m.put("list", list);
		}
		return m;
	}
	
}
