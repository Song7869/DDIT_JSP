package kr.or.ddit.common.controller;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LogoutController{
	
	@RequestMapping(value="/login/logout.do", method=RequestMethod.POST)
	public String doPost(HttpSession session, HttpServletResponse resp) throws IOException{
		if(session.isNew()) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
		session.invalidate();
		String message = URLEncoder.encode("로그아웃 성공", "UTF-8");
		return "redirect:/?message="+message;
	}
}












