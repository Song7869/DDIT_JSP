package kr.or.ddit.member.controller;

import java.io.IOException;

import javax.inject.Inject;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.validate.groups.UpdateGroup;
import kr.or.ddit.vo.MemberVO;

@Controller
@RequestMapping("/member/memberUpdate.do")
public class MemberUpdateController{
	@Inject
	private MemberService service;
	
	@ModelAttribute("command")
	public String commandId() {
		return "UPDATE";
	}
	
	@RequestMapping
	public String doGet(@AuthenticationPrincipal(expression="authMember") MemberVO authMember
			, Model model){	
		MemberVO member = service.retrieveMember(authMember.getMemId());
		
		model.addAttribute("member", member);
		
		return "member/memberForm";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String doPost(@Validated(UpdateGroup.class) @ModelAttribute("member") MemberVO member,
			Errors errors,
			Model model) throws IOException{
		String viewName = null;
		String message = null;
		if(!errors.hasErrors()) {
//		3-1. 통과
//			로직 사용
			ServiceResult result = service.modifyMember(member);
			switch(result) {
			case INVALIDPASSWORD:
//			비번오류 : memberForm 으로 이동(기존 데이터 + 메시지 전달).
				viewName = "member/memberForm";
				message = "비번 오류";
				break;
			case OK:
//			OK : 웰컴 페이지로 이동
				viewName = "redirect:/mypage.do";
				break;
			default:
//			FAIL : memberForm 으로 이동(기존 데이터 + 메시지 전달).
				viewName = "member/memberForm";
				message = "서버 오류, 잠시뒤 다시 해보셈.";
			}
			
		}else {
//		3-2. 불통
//			: memberForm으로 이동 (기존데이터 + 검증 결과 메시지 전달)
			viewName = "member/memberForm";
			
		}
		
		model.addAttribute("message", message);
		
		return viewName;		
 	}


}
