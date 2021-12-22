package kr.or.ddit.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;

@WebServlet("/member/memberDelete.do")
public class MemberDeleteServlet extends HttpServlet {
	private MemberService service = new MemberServiceImpl();
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		MemberVO authMember = (MemberVO) session.getAttribute("authMember");
		String memPass = req.getParameter("memPass");
		if(StringUtils.isBlank(memPass)) {
			resp.sendError(400);
			return;
		}
	 	ServiceResult result = service.removeMember(new MemberVO(authMember.getMemId(), memPass));
	 	String viewName = null;
	 	String message = null;
	 	switch (result) {
		case INVALIDPASSWORD:
			viewName = "redirect:/mypage.do";
			message = "비번 오류";
			session.setAttribute("message", message);
			break;
		case OK:
			session.invalidate();
			viewName = "redirect:/";
			break;
		default:
			viewName = "redirect:/mypage.do";
			message = "서버 오류";
			session.setAttribute("message", message);
			break;
		}
	 	
	 	if(viewName.startsWith("redirect:")) {
			viewName = viewName.substring("redirect:".length());
			resp.sendRedirect(req.getContextPath() + viewName);
		}else {
			String prefix = "/WEB-INF/views/";
			String suffix = ".jsp";
			req.getRequestDispatcher(prefix+viewName+suffix).forward(req, resp);
		}
	}
}













