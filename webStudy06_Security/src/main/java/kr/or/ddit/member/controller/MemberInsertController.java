package kr.or.ddit.member.controller;

import java.io.IOException;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.validate.groups.InsertGroup;
import kr.or.ddit.vo.MemberVO;


@Controller
@RequestMapping("/member/memberInsert.do")
public class MemberInsertController{
	
	@Inject
	private MemberService service;
	
	@ModelAttribute("command")
	public String commandId() {
		return "INSERT";
	}
	
	@RequestMapping
	public String form(){
		return "member/memberForm";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String process(@Validated(InsertGroup.class) @ModelAttribute("member") MemberVO member
			, BindingResult errors 
			, Model model) throws IOException{
		
		String viewName = null;
		String message = null;
		if(!errors.hasErrors()) {
//		3-1. 통과
//			로직 사용
			ServiceResult result = service.createMember(member);
			switch(result) {
			case PKDUPLICATED:
//			PK중복 : memberForm 으로 이동(기존 데이터 + 메시지 전달).
				viewName = "member/memberForm";
				message = "아이디 중복";
				break;
			case OK:
//			OK : 웰컴 페이지로 이동
				viewName = "redirect:/";
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














