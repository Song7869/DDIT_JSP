package kr.or.ddit.member.controller;

import javax.annotation.Resource;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.vo.MemberVO;

@Controller
public class MypageController{
	@Resource(name="memberServiceImpl")
	private MemberService service;
	
	@RequestMapping("/mypage.do")
	public String myPageHandler(Model model, Authentication authentication){
		String memId = authentication.getName();
		
		MemberVO detail = service.retrieveMember(memId);
		
		model.addAttribute("member", detail);
		
		return "member/mypage";
	}
}


















