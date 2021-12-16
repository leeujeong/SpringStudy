package com.koreait.integration1.repository;

import java.util.List;
import java.util.Map;

import com.koreait.integration1.domain.SearchBoard;

public interface SearchBoardRepository {

	public List<SearchBoard> findAllBoard();
	public List<SearchBoard> findBoard(Map<String, Object> map);
}
