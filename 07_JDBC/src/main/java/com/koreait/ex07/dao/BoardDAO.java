package com.koreait.ex07.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.koreait.ex07.dto.Board;



public class BoardDAO {

	private DataSource dataSource;
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private String sql;
	
	//스프링이 bean을 생성할때 singleton으로 생성한다. 따라서 singleton코드는 작성하지 않음
	
	public BoardDAO() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/oracle");	//타입이 안맞아서 캐스팅 필요
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public void close(Connection con, PreparedStatement ps, ResultSet rs) {
		try {
			if (con != null) con.close();
			if (ps != null) ps.close();
			if (rs != null) rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<Board> selectBoardList() {
		List<Board> list = new ArrayList<Board>();
		try {
			con = dataSource.getConnection();
			sql = "SELECT NO, WRITER, TITLE, CONTENT, CREATED, LASTMODIFIED FROM BOARD";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Board board = new Board(rs.getLong(1), 
						rs.getString(2), 
						rs.getString(3), 
						rs.getString(4), 
						rs.getString(5),
						rs.getString(6));
				list.add(board);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, rs);
		}
		return list;
	}
	
	
	public int insertBoard(Board board) {
		int result=0; 
		try {
			con=dataSource.getConnection();
			sql= "INSERT INTO BOARD VALUES(BOARD_SEQ.nextVAL, ?, ?, ?,TO_CHAR(SYSDATE,'YYYY-MM-DD'), TO_CHAR(SYSDATE,'YYYY-MM-DD'))";
			ps = con.prepareStatement(sql);
			ps.setNString(1, board.getWriter());
			ps.setNString(2, board.getTitle());
			ps.setNString(3, board.getContent());
			result = ps.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(con, ps, rs);
		}
		return result;
	}
	
	
	public Board selectBoardByNo(Long no) {	
		Board board = null;
		try {
			con = dataSource.getConnection();
			sql = "SELECT NO, WRITER, TITLE, CONTENT, CREATED, LASTMODIFIED FROM BOARD WHERE NO = ?";
			ps = con.prepareStatement(sql);
			ps.setLong(1, no);
			rs = ps.executeQuery();
			if (rs.next()) {
				board = new Board(rs.getLong(1), 
						rs.getString(2), 
						rs.getString(3), 
						rs.getString(4), 
						rs.getString(5),
						rs.getString(6));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, rs);
		}
		return board;
	}
	
	public int updateBoard(Board board) {
		int result=0;
		try {
			con=dataSource.getConnection();
			sql= "UPDATE BOARD SET TITLE =?, CONTENT=?, LASTMODIFIED = TO_CHAR(SYSDATE, 'YYYY-MM-DD') WHERE NO=?";
			ps = con.prepareStatement(sql);
			ps.setNString(1, board.getTitle());
			ps.setNString(2, board.getContent());
			ps.setLong(3, board.getNo());
			result = ps.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(con, ps, rs);
		}
		return result;
	}
	
	
	public int deleteBoard(Long no) {
		int result=0;
		try {
			con=dataSource.getConnection();
			sql= "DELETE FROM BOARD WHERE NO=?";
			ps = con.prepareStatement(sql);
			ps.setLong(1, no);
			result = ps.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(con, ps, rs);
		}
		return result;
	}
}
