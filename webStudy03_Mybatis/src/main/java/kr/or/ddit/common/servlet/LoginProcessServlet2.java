package kr.or.ddit.common.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login/loginProcess2.do")
public class LoginProcessServlet2 extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//1. 클라이언트 파라미터 받기
		String mem_id;
		String mem_pass;
		mem_id = req.getParameter("mem_id");
		mem_pass = req.getParameter("mem_pass");
		if(mem_id == null || mem_id.isEmpty() || mem_id =="") {
			try {
				resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
				return;
			} catch (Exception e) {

			}
				
			return;
		}
		//2. 파라미터 검증
		if (mem_pass == null || mem_pass.isEmpty() || mem_pass == "") {
			try {
				resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "올바르지 못한 값이 넘어왔습니다.");	
				return;
			} catch (Exception e) {
			}
			return;
		}
		
		System.out.println("id : "+mem_id);
		System.out.println("pass: "+mem_pass);
		

		
		//3. 파라미터 인증(아이디 == 비밀번호)
		if(mem_id.equals(mem_pass)) {
			//4. 인증성공 (redirect) -> 웰컴페이지로 이동
			System.out.println("인증 성공!");
			String location = req.getContextPath()+"/index.jsp";
			resp.sendRedirect(location);
		}
		else {
			//5. 인증실패/(다시 입력받을 페이지로 이동/forward) 
			System.out.println("인증 실패...");
			req.setAttribute("mem_id", mem_id);
			req.setAttribute("mem_pass", mem_pass);
			
			String path = "/login/loginForm.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(path);
			rd.forward(req, resp);
		}
		
		

	}
}
