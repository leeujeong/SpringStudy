package com.koreait.test1.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

public interface BoardService {

	public void execute(SqlSession sqlSession, Model model);
	
}
