package kr.or.ddit.common.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.AuthenticateService;
import kr.or.ddit.member.service.AuthenticateServiceImpl;
import kr.or.ddit.vo.MemberVO;

@Controller
public class LoginProcessController{
	
	@Inject
	private AuthenticateService service;
	
	@RequestMapping(value="/login/loginProcess.do", method=RequestMethod.POST)
	public String doPost(
			@RequestParam(required=true) String memId,
			@RequestParam(required=true) String memPass,
			HttpSession session){
		
		MemberVO member = new MemberVO(memId, memPass);
		boolean valid = validate(member);
		String location = null;
		String message = null;
		if(valid) {
			ServiceResult result = service.authenticated(member);
			if(ServiceResult.OK.equals(result)) {
				location = "redirect:/index.jsp";
				session.setAttribute("authMember", member);
			}else {
				location = "redirect:/login/loginForm.jsp";
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
			location = "redirect:/login/loginForm.jsp";
			message = "아이디나 비번이 누락됐음.";
		}
		
		session.setAttribute("message", message);
		return location;
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

















