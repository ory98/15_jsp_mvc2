<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--

	# MVC2 데이터베이스 연동예시


	1. 이클립스에서 해당 프로젝트 > WebContent > WEB-INF > lib폴더에 아래의 라이브러리 추가 
		
		commons-collections4-4.1.jar
		commons-dbcp2-2.2.0.jar
		commons-pool2-2.5.0.jar
		jstl-1.2.jar
		mysql-connector-java-8.0.15.jar
		
	
	2. 이클립스에서 Servers폴더에 있는 Context.xml파일에 아래의 내용 추가 
	
		<Resource 
			auth="Container" 
			driverClassName="com.mysql.cj.jdbc.Driver"
			loginTimeout="10" 
			maxWait="5000" 
			name="jdbc/pool1" 
			username="root"
			password="1234" 
			type="javax.sql.DataSource"
			url="jdbc:mysql://호스트명:포트번호/데이터베이스명?serverTimezone=UTC"
		/> 
 
 
 	3. DAO 클래스에서 아래의 코드 추가
 	
	 	private Connection conn 		= null;
	    private PreparedStatement pstmt = null;
	    private ResultSet rs 			= null;
	    
	    public Connection getConnection() {
	        
	    	try {
	    		
	    		Context initCtx = new InitialContext();
	    		Context envCtx = (Context)initCtx.lookup("java:comp/env");
	    		DataSource ds = (DataSource)envCtx.lookup("연결명");   
	    		conn = ds.getConnection();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
	        
	        return conn;
	        
	    }
 
 --%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인</title>
</head>
<body>

	<div align="center">
		<c:choose>
			<c:when test="${sessionScope.id eq null }">
				<a href="join">회원가입</a><br><br>
				<a href="login">로그인</a>
			</c:when>
			<c:otherwise>
				<a href="update">입사지원정보 수정</a><br><br>
				<a href="logout">로그아웃</a><br><br>
				<a href="delete">탈퇴</a>
			</c:otherwise>
		</c:choose>
	</div>
	
	<hr><br><br><br>
	
	<div align="center">
		<a href="apply"><img src="img/applyonline.png" alt="입사지원"></a>
	</div>
	
</body>
</html>


