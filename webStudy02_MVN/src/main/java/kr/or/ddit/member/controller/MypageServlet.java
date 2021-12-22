package kr.or.ddit.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;

@WebServlet("/mypage.do")
public class MypageServlet extends HttpServlet{
	private MemberService service = new MemberServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		MemberVO authMember = (MemberVO)req.getSession().getAttribute("authMember");
		MemberVO detail = service.retrieveMember(authMember.getMemId());
		
		req.setAttribute("member", detail);
		
		String viewName = "member/mypage";
		
		String prefix = "/WEB-INF/views/";
		String suffix = ".jsp";
		
		req.getRequestDispatcher(prefix+viewName+suffix).forward(req, resp);
	}
	
	
}
