package kr.or.ddit.common.servlet;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login/logout.do")
public class LogoutServlet extends HttpServlet{

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("서블릿 넘어옴");
		
		HttpSession session = req.getSession();
		if(session.isNew()) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		
		//session.removeAttribute("authMember");
		session.invalidate(); // 세션스코프 지우기, 세션만료, 세션ID 비우기
		//getServletContext().setAttribute("message", "로그아웃 성공");
		String message = java.net.URLEncoder.encode("로그아웃 성공", "UTF-8");
		resp.sendRedirect(req.getContextPath() + "/?message=" + message); // 웰컴페이지로 다시 보기
		
	}
}
