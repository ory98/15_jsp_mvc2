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

@WebServlet("/update")
public class Update extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		MemberDto memberDto = MemberDao.getInstance().getOneMemberInfo((String)session.getAttribute("id"));
		
		if (memberDto.getField() != null) { // 지원분야가 없으면 > 최초지원
			
			String[] skills = memberDto.getSkill().split(","); // ,로 나누기 
			for (String skill : skills) {
				
				if(skill.equals("html")) 		request.setAttribute("html", true);
				if(skill.equals("css")) 		request.setAttribute("css", true);
				if(skill.equals("javascript")) 	request.setAttribute("javascript", true);
				if(skill.equals("java")) 		request.setAttribute("java", true);
				if(skill.equals("jsp")) 		request.setAttribute("jsp", true);
				if(skill.equals("spring"))		request.setAttribute("spring", true);
				
			}
			
			request.setAttribute("memberDto", memberDto);
			request.setAttribute("isFirstApply", false);
		}
		else {
			request.setAttribute("isFirstApply", true);
		}
		
		RequestDispatcher dis = request.getRequestDispatcher("step1_01_login/10_update.jsp");
		dis.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		MemberDto memberDto = new MemberDto();
		
		memberDto.setId(request.getParameter("id"));
		memberDto.setPw(request.getParameter("pw"));
		memberDto.setName(request.getParameter("name"));
		memberDto.setTel(request.getParameter("tel"));
		memberDto.setEmail(request.getParameter("email"));
		memberDto.setMajor(request.getParameter("major"));
		memberDto.setField(request.getParameter("field"));
		
		String[] temp = request.getParameterValues("skill");
		String skill = "";
		for (int i = 0; i < temp.length; i++) {
			skill += temp[i];
			if (i != temp.length -1) { //i가 마지막이 아니라면 ,를 더해라
				skill += ",";
			}
		}
		memberDto.setSkill(skill);
		
		MemberDao.getInstance().updateMember(memberDto);
		
		RequestDispatcher dis = request.getRequestDispatcher("step1_01_login/11_updateAction.jsp");
		dis.forward(request, response);
	}

}
