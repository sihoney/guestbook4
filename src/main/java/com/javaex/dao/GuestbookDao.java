package com.javaex.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestbookVo;

@Repository
public class GuestbookDao {
	
	/////////////////////
	// field
	/////////////////////
//	@Autowired
//	private DataSource dataSource;
	@Autowired
	private SqlSession sqlSession;
	
	
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
	public List<GuestbookVo> getList() {
		
		List<GuestbookVo> gList = sqlSession.selectList("guestbook.selectAll");
		return gList;
	}
	
	public void delete(GuestbookVo vo) {
		
		int count = sqlSession.delete("guestbook.delete", vo);
		System.out.println(count + "건이 삭제되었습니다.");
	}
	
	public void addGuest(GuestbookVo gvo) {
		
		int count = sqlSession.insert("guestbook.insert", gvo);
		System.out.println(count + "건이 추가되었습니다.");
	}


//	public GuestbookVo getGuest(int no) {
//		
//		GuestbookVo gvo = null;
//		this.getConnection();
//		
//		try {
//	         /*****************************
//	          3. SQL문 준비 / 바인딩 / 실행
//	         ******************************/
//			String query = "";
//			query += " select password ";
//			query += " from guestbook ";
//			query += " where no = ? ";
//			
//			pstmt = conn.prepareStatement(query);
//			
//			pstmt.setInt(1, no);
//			
//			rs = pstmt.executeQuery();
//			
//	         /******************
//	         4.결과처리
//	         *******************/
//			while(rs.next()) {
//				String pw = rs.getString("password");
//				
//				gvo = new GuestbookVo("", pw, "");
//			}
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		
//		this.close();
//		
//		return gvo;
//	}
}
