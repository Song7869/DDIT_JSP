package kr.or.ddit.member.controller;

import java.awt.RadialGradientPaint;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;


@WebServlet("/member/memberList.do")
public class MemberListServlet extends HttpServlet{

	private MemberService service = new MemberServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		List<MemberVO> result = service.retrieveMemberList();
		
		req.setAttribute("memberList", result);
		
		String viewName = "member/memberList";
		
		String prefix = "/WEB-INF/views/";
		String suffix = ".jsp";
		
		
		req.getRequestDispatcher(prefix+viewName+suffix).forward(req, resp);
	}
}
