package kr.or.ddit.common.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.AuthenticateService;
import kr.or.ddit.member.service.AuthenticateServiceImpl;
import kr.or.ddit.vo.MemberVO;

@WebServlet("/login/loginProcess.do")
public class LoginProcessServlet extends HttpServlet{
	
	private AuthenticateService service = new AuthenticateServiceImpl();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String mem_id = req.getParameter("memId");
		String mem_pass = req.getParameter("memPass");
		MemberVO member = new MemberVO(mem_id, mem_pass);
		
		
		boolean valid = validate(member);
		String location = null;
		String message = null;
		HttpSession session = req.getSession();
		if(valid) {
			ServiceResult result = service.authenticated(member);
			if(ServiceResult.OK.equals(result)) {
				location = "/index.jsp";
				session.setAttribute("authMember", member);
			}else {
				location = "/login/loginForm.jsp";
				session.setAttribute("failId", member.getMemId());
				if(ServiceResult.NOTEXIST.equals(result)) {
					message = "아이디가 잘못됐음. 확인하셈.";
				}else {
					message = "비밀번호가 잘못됐음. 확인하셈.";
				}
//				req.getRequestDispatcher(dest).forward(req, resp);
			}
		}else {
//			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			location = "/login/loginForm.jsp";
			message = "아이디나 비번이 누락됐음.";
		}
		
		session.setAttribute("message", message);
		resp.sendRedirect(req.getContextPath() + location);
	}

	private boolean validate(MemberVO member) {
		boolean valid = true;
		if(StringUtils.isBlank(member.getMemId())) {
			valid = false;
		}
		if(StringUtils.isBlank(member.getMemPass())) {
			valid = false;
		}
		return valid;
	}
}

















