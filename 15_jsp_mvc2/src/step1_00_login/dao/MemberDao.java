package step1_00_login.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import step1_00_login.dto.MemberDto;

public class MemberDao {
	
	private MemberDao() {}
	private static MemberDao instance = new MemberDao();
	public static MemberDao getInstance() {
		return instance;
	}

	private Connection conn 		= null;
	private PreparedStatement pstmt = null;
	private ResultSet rs 			= null;
	
	public Connection getConnection() {
        
    	try {
    		
//    		import javax.naming.Context;
//    		import javax.naming.InitialContext;
//    		import javax.sql.DataSource;
    		
    		// 연결을 끊지 않고 쓸때마다 찾아서(lookup) 꺼내서 쓸 수 있다.
    		Context initCtx = new InitialContext();
    		Context envCtx = (Context)initCtx.lookup("java:comp/env"); // lookup 메서드를 통해 context.xml 파일에 접근하여 자바환경 코드를 검색
    		DataSource ds = (DataSource)envCtx.lookup("jdbc/pool1");  // name명 <Context>태그안의 <Resource> 환경설정의 name이 jdbc/pool1인 것을 검색
    		conn = ds.getConnection();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        return conn;
        
    }
	
	// 1. 회원가입 DAO
	public boolean joinMember(MemberDto memberDto) {
		
		boolean isJoin = false;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM MEMBER WHERE ID =?");
			pstmt.setString(1, memberDto.getId());
			rs = pstmt.executeQuery();
			
			if (!rs.next()) { // 중복된 아이디가 없다면
				pstmt = conn.prepareStatement("INSERT INTO MEMBER(ID,PW,NAME,TEL,EMAIL) VALUES(?,?,?,?,?)");
				pstmt.setString(1, memberDto.getId()); 
				pstmt.setString(2, memberDto.getPw());
				pstmt.setString(3, memberDto.getName());
				pstmt.setString(4, memberDto.getTel());
				pstmt.setString(5, memberDto.getEmail());
				pstmt.executeUpdate();
				isJoin = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)    try{rs.close();} catch (SQLException e) {e.printStackTrace();}
			if (pstmt != null) try{pstmt.close();} catch (SQLException e) {e.printStackTrace();}
			if (conn != null)  try{conn.close();} catch (SQLException e) {e.printStackTrace();}
		}
		return isJoin;
	}
	
	// 2. 로그인 DAO
	public boolean login(MemberDto memberDto) {
		
		boolean isLogin = false;
		
		try {
			conn = getConnection();
			
			pstmt = conn.prepareStatement("SELECT * FROM MEMBER WHERE ID = ? AND PW = ?");
			pstmt.setString(1, memberDto.getId());
			pstmt.setString(2, memberDto.getPw());
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				isLogin = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)    try{rs.close();} catch (SQLException e) {e.printStackTrace();}
			if (pstmt != null) try{pstmt.close();} catch (SQLException e) {e.printStackTrace();}
			if (conn != null)  try{conn.close();} catch (SQLException e) {e.printStackTrace();}
		}
		
		
		return isLogin;
		
	}
	
	// 3. 한명의 회원 정도 조회 DAO 
	public MemberDto getOneMemberInfo(String id) {
		
		MemberDto memberDto = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM MEMBER WHERE ID = ?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				
				memberDto = new MemberDto();
				memberDto.setId(rs.getString("ID"));
				memberDto.setPw(rs.getString("PW"));
				memberDto.setName(rs.getString("NAME"));
				memberDto.setTel(rs.getString("TEL"));
				memberDto.setEmail(rs.getString("TEL"));
				memberDto.setField(rs.getString("FIELD"));
				memberDto.setSkill(rs.getString("SKILL"));
				memberDto.setMajor(rs.getString("MAJOR"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)    try{rs.close();} catch (SQLException e) {e.printStackTrace();}
			if (pstmt != null) try{pstmt.close();} catch (SQLException e) {e.printStackTrace();}
			if (conn != null)  try{conn.close();} catch (SQLException e) {e.printStackTrace();}
		}
		
		return memberDto;
		
	}
	
	// 4. 입사지원 DAO
	public void apply(MemberDto memberDto) {
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("UPDATE MEMBER SET FIELD = ?, SKILL = ?, MAJOR = ? WHERE ID = ?");
			pstmt.setString(1, memberDto.getField());
			pstmt.setString(2, memberDto.getSkill());
			pstmt.setString(3, memberDto.getMajor());
			pstmt.setString(4, memberDto.getId());
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) try{pstmt.close();} catch (SQLException e) {e.printStackTrace();}
			if (conn != null)  try{conn.close();} catch (SQLException e) {e.printStackTrace();}
		}

	}
	
	// 5. 회원탈퇴 DAO
	public void deleteMember(String id) {
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("DELETE FROM MEMBER WHERE ID =?");
			pstmt.setString(1, id);
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) try{pstmt.close();} catch (SQLException e) {e.printStackTrace();}
			if (conn != null)  try{conn.close();} catch (SQLException e) {e.printStackTrace();}
		}
	}
	
	// 6. 회원정보 수정 DAO
	public void updateMember(MemberDto memberDto) {
		
		try {
			
			conn = getConnection();
			pstmt = conn.prepareStatement("UPDATE MEMBER SET PW = ?, NAME = ?, TEL = ?, EMAIL = ?, FIELD = ?, SKILL = ?, MAJOR = ? WHERE ID = ?");
			pstmt.setString(1, memberDto.getPw());
			pstmt.setString(2, memberDto.getName());
			pstmt.setString(3, memberDto.getTel());
			pstmt.setString(4, memberDto.getEmail());
			pstmt.setString(5, memberDto.getField());
			pstmt.setString(6, memberDto.getSkill());
			pstmt.setString(7, memberDto.getMajor());
			pstmt.setString(8, memberDto.getId());
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) try{pstmt.close();} catch (SQLException e) {e.printStackTrace();}
			if (conn != null)  try{conn.close();} catch (SQLException e) {e.printStackTrace();}
		}
		
	}
	
	
}
