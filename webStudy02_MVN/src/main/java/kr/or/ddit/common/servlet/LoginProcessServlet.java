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
		//1. 클라이언트 파라미터 받기
		String mem_id = req.getParameter("mem_id");
		String mem_pass = req.getParameter("mem_pass");
		MemberVO member = new MemberVO(mem_id, mem_pass);

		
		boolean valid = validate(member);
		String location = null;
		String message = null;
		HttpSession session = req.getSession();
		
		if(valid) {
			//파라미터 검증 통과후
			ServiceResult result = service.authenticated(member);
			if(ServiceResult.OK.equals(result)) {
				//파라미터 인증 통과
				location ="/index.jsp";
				session.setAttribute("authMember", member);
			}
			else {
				//파라미터 인증 통과 못함
				location ="/login/loginForm.jsp";
				session.setAttribute("failId", member.getMemId());
				
				if(ServiceResult.NOTEXTIST.equals(result)) {
					message = "아이디가 잘못되었습니다.";
				}
				else {
					message = "비밀번호가 잘못되었습니다.";
				}
			}
		}
		else {
			//검증 통과 못함
			//resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			location ="/login/loginForm.jsp";
			message = "아이디나, 비밀번호가 누락되었습니다.";
			
			
		}
		
		session.setAttribute("message", message);
		resp.sendRedirect(req.getContextPath() + location);

	}
	
	private boolean validate(MemberVO member) {
		boolean valid = true;
		//StringUtils.isBlank 를 쓰면 공백, 화이트스페이스, null 모두 체크한다.
		//.isEmpty 는 trim을 하지 않아서 화이트스페이스를 인식하지 못한다.
		//->commons-lang3-3.12.0.jar
		if(StringUtils.isBlank(member.getMemId())) {
			valid = false;
		}
		if(StringUtils.isBlank(member.getMemPass())) {
			valid = false;
		}
		return valid;
	}
}






























