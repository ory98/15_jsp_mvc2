package step1_00_login.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import step1_00_login.dao.MemberDao;
import step1_00_login.dto.MemberDto;

@WebServlet("/login")
public class Login extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		RequestDispatcher dis = request.getRequestDispatcher("step1_01_login/04_login.jsp");
		dis.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		MemberDto memberDto = new MemberDto();
		
		memberDto.setId(request.getParameter("id"));
		memberDto.setPw(request.getParameter("pw"));
		
		boolean isLogin = MemberDao.getInstance().login(memberDto);
		
		if(isLogin == true) {
			
			HttpSession session = request.getSession();
			session.setAttribute("id", memberDto.getId());
		}
		
		RequestDispatcher dis = request.getRequestDispatcher("step1_01_login/05_loginAction.jsp");
		dis.forward(request, response);
	}

}
