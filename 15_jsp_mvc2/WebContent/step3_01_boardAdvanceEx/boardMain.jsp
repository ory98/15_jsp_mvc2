<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>boardMain</title>
</head>
<body>

	<%-- 
		
		<Resource 
			auth="Container" 
			driverClassName="com.mysql.cj.jdbc.Driver"
			loginTimeout="10" 
			maxWait="5000" 
			name="jdbc/pool3" 
			username="root"
			password="1234" 
			type="javax.sql.DataSource"
			url="jdbc:mysql://localhost:3306/MVC2_BOARD_ADVANCE_EX?serverTimezone=UTC"
		/> 
	
	--%>

	<div align="center" style="padding-top: 100px">
		<img src="img/jsp.PNG" alt="jsp심볼" width="800px" height="500px"><br><br><br><br>
		<input type="button" value="게시판 보기" onclick="location.href='boardList'">
	</div>
</body>
</html>