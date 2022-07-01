package step1_00_login.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import step1_00_login.dao.MemberDao;
import step1_00_login.dto.MemberDto;

@WebServlet("/join")
public class Join extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
	// 화면으로 보여줌
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher dis = request.getRequestDispatcher("step1_01_login/02_join.jsp");
		dis.forward(request, response);
	
	}
	
	// DB에 넣어줌
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		// 데이터 넣어줌 
		MemberDto memberDto = new MemberDto();
		
		memberDto.setId(request.getParameter("id"));
		memberDto.setPw(request.getParameter("pw"));
		memberDto.setName(request.getParameter("name"));
		memberDto.setTel(request.getParameter("tel"));
		memberDto.setEmail(request.getParameter("email"));
		
		// 넣은 데이터로 사용하기
		boolean isJoin = MemberDao.getInstance().joinMember(memberDto);
		request.setAttribute("isJoin", isJoin);
		
		RequestDispatcher dis = request.getRequestDispatcher("step1_01_login/03_joinAction.jsp");
		dis.forward(request, response);
	
	}

}
