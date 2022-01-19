package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestbookVo;

@Repository
public class GuestbookDao {
	
	/////////////////////
	// field
	/////////////////////
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	/////////////////////
	// constructor
	/////////////////////
	public GuestbookDao() {
		
	}
	
	
	/////////////////////
	// method
	/////////////////////
	public void getConnection() {
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public void close() {
		try {

			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}
	
	public List<GuestbookVo> getList(){
		
		this.getConnection();
		List<GuestbookVo> list = new ArrayList<>();
		
		try {
	         /*****************************
	          3. SQL문 준비 / 바인딩 / 실행
	         ******************************/
			String query = "";
			query += " select no, ";
			query += "  	  name, ";
			query += " 		  password, ";
			query += "        content, ";
			query += "        to_char(reg_date, 'yyyy-mm-dd hh:mi:ss') reg_date ";
			query += " from guestbook ";
			
			pstmt = conn.prepareStatement(query);
			
			rs = pstmt.executeQuery();
			
	         /******************
	         4.결과처리
	         *******************/
			while(rs.next()) {
				int no = rs.getInt("no");
				String name = rs.getString("name");
				String password = rs.getString("password");
				String content = rs.getString("content");
				String date = rs.getString("reg_date");
				
				GuestbookVo gvo = new GuestbookVo(no, name, password, content, date);
				list.add(gvo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		this.close();
		return list;
	}
	
	
	public void addGuest(GuestbookVo gvo) {
		
		this.getConnection();
		
		try {
			/*********************************
			 3. SQL문 준비 / 바인딩 / 실행
			**********************************/
			String query = "";
			query += " insert into guestbook ";
			query += " values(seq_guestbook_no.nextval, ?, ?, ?, sysdate) ";
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, gvo.getName());
			pstmt.setString(2, gvo.getPassword());
			pstmt.setString(3, gvo.getContent());
			
			int count = pstmt.executeUpdate();
			
	        /*****************
			 4.결과처리
			******************/
			System.out.println(count + "건이 저장되었습니다.");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		this.close();
	}
	
	public void deleteGuest(int no) {
		
		this.getConnection();
		
		try {
			/*********************************
			 3. SQL문 준비 / 바인딩 / 실행
			**********************************/
			String query = "";
			query += " delete from guestbook ";
			query += " where no = ? ";
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, no);
			
			int count = pstmt.executeUpdate();
			
	        /*****************
			 4.결과처리
			******************/
			System.out.println(count + "건이 삭제되었습니다.");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		this.close();
	}
	
	
	public int delete(GuestbookVo vo) {
		
		int count = 0;
		this.getConnection();
		
		try {
			
			String query = "";
			query += " delete from guestbook ";
			query += " where no = ? ";
			query += " and password = ? ";
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, vo.getNo());
			pstmt.setString(2, vo.getPassword());
			
			count = pstmt.executeUpdate();
			
			System.out.println(count + "건이 삭제되었습니다.");
			
		} catch (SQLException e) {
			System.out.println("error: " + e);
		}
		
		this.close();
		
		return count;
	}
	 
	
	public GuestbookVo getGuest(int no) {
		
		GuestbookVo gvo = null;
		this.getConnection();
		
		try {
	         /*****************************
	          3. SQL문 준비 / 바인딩 / 실행
	         ******************************/
			String query = "";
			query += " select password ";
			query += " from guestbook ";
			query += " where no = ? ";
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, no);
			
			rs = pstmt.executeQuery();
			
	         /******************
	         4.결과처리
	         *******************/
			while(rs.next()) {
				String pw = rs.getString("password");
				
				gvo = new GuestbookVo("", pw, "");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		this.close();
		
		return gvo;
	}
}
