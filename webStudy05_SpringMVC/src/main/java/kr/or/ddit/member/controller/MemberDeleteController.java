package kr.or.ddit.member.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.vo.MemberVO;

@Controller
public class MemberDeleteController{
	@Inject
	private MemberService service;
	
	@RequestMapping(value="/member/memberDelete.do", method=RequestMethod.POST)
	public String delete(@SessionAttribute(name="authMember",required=true) MemberVO authMember, 
			@RequestParam("memPass") String memPass
			, RedirectAttributes redirectAttributes
			, HttpSession session
			){
	 	ServiceResult result = service.removeMember(new MemberVO(authMember.getMemId(), memPass));
	 	String viewName = null;
	 	String message = null;
	 	switch (result) {
		case INVALIDPASSWORD:
			viewName = "redirect:/mypage.do";
			message = "비번 오류";
			redirectAttributes.addFlashAttribute("message", message);
			break;
		case OK:
			session.invalidate();
			viewName = "redirect:/";
			break;
		default:
			viewName = "redirect:/mypage.do";
			message = "서버 오류";
			redirectAttributes.addFlashAttribute("message", message);
			break;
		}
	 	
	 	return viewName;
	}
}













