package com.javarudals.ex;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberDao {
	
	
	static String driverName = "com.mysql.jdbc.Driver";
	static String url = "jdbc:mysql://localhost:3306/memberdb";
	static String user = "root";
	static String password = "kjs1111";
	
	
	private static MemberDao instance = new MemberDao();//싱글턴 패턴
	
	private MemberDao() {		
		
	}
	
	public static MemberDao getInstance() {
		
		return instance;
	}

	public int insertMember(MemberDto dto) {		
		
		String m_id = dto.getId();
		String m_pw = dto.getPw();
		String m_name = dto.getName();
		String m_eMail = dto.geteMail();
		String m_address = dto.getAddress();
		
		int flag = 0;
		
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		String query = "insert into members(id, pw, name, eMail, address) values('"+ m_id + "','"+ m_pw +"','" + m_name + "','" + m_eMail + "','"+ m_address + "')";
//		String query = "insert into members(id, pw, name, eMail, address) values(?, ?, ?, ?, ?)";
		
		
		try{
			Class.forName(driverName);
			connection = DriverManager.getConnection(url, user, password);
			pstmt = connection.prepareStatement(query);//sql을 실행시켜주는 객체 생성(Statement)
//			pstmt.setString(1, m_id);
//			pstmt.setString(2, m_pw);
//			pstmt.setString(3, m_name);
//			pstmt.setString(4, m_eMail);
//			pstmt.setString(5, m_address);			
			
			flag = pstmt.executeUpdate(query);//sql문이 성공적으로 실행되면 flag=1을 반환
			
		} catch(Exception e) {
			e.printStackTrace();		
		} finally {
			try{
				if(pstmt != null){					
					pstmt.close();
				}
				if(connection != null){					
					connection.close();
				}
			} catch(Exception e2) {
				e2.printStackTrace();
			}
			
		}
		
		return flag;		
	}
	
	
	public int confirmId(String id) {
		int flag = 0;
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;
		String query = "select id from members where id = ?";
		
		try {
			Class.forName(driverName);
			connection = DriverManager.getConnection(url, user, password);
			pstmt = connection.prepareStatement(query);//sql을 실행시켜주는 객체 생성(Statement)
			pstmt.setString(1, id);
			set = pstmt.executeQuery();
			
			if(set.next()) {//조건이 참이면 DB에 이미 똑같은 아이디 있음
				flag = 1;
			} else {
				flag = 0;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return flag;
	}
	
	public int userCheck(String id, String pw) {
		int flag = 0;
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;
		String query = "select pw from members where id = ?";
		
		try {
			Class.forName(driverName);
			connection = DriverManager.getConnection(url, user, password);
			pstmt = connection.prepareStatement(query);//sql을 실행시켜주는 객체 생성(Statement)
			pstmt.setString(1, id);
			set = pstmt.executeQuery();
			
			if(set.next()) {//조건이 참이면 DB에 이미 똑같은 아이디 있음
				
				String dbpw = set.getString("pw");//DB에서 가져온 해당 id의 pw값이 저장
				if(dbpw.equals(pw)) {
					flag = 1;//로그인 성공(아이디와 비번이 일치함)
				} else {
					flag = 0;//아이디는 맞으나 비번이 일치하지 않음
				}				
				
			} else {
				flag = -1;//회원 가입 사실이 없음(DB에 아이디가 없음)
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return flag;
	}
		
	
}