package com.koreait.test1.dao;

import java.util.ArrayList;

import com.koreait.test1.dto.Board;

public class BoardDAO {

	public ArrayList<Board> selectBoardList() { return null; }
	public int selectBoardCount() { return 0; }
	public int insertBoard(String writer, String title, String content) { return 0; }
	public Board selectByIdx(int idx) { return null; }
	public int updateBoard(String title, String content, int idx) { return 0; }
	public int deleteBoard(int idx) { return 0; }
	
}
