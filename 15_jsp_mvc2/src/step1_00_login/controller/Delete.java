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

@WebServlet("/delete")
public class Delete extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		MemberDao.getInstance().deleteMember((String)session.getAttribute("id"));
		
		session.invalidate();
		
		RequestDispatcher dis = request.getRequestDispatcher("step1_01_login/09_delete.jsp");
		dis.forward(request, response);
		
	}

}
