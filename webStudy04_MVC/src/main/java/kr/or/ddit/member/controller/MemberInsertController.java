package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.resolvers.ModelAttribute;
import kr.or.ddit.mvc.annotation.resolvers.RequestPart;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.mvc.fileupload.MultipartFile;
import kr.or.ddit.util.ValidateUtils;
import kr.or.ddit.validate.groups.InsertGroup;
import kr.or.ddit.vo.MemberVO;


@Controller
public class MemberInsertController{
	
	private MemberService service = new MemberServiceImpl();
	
	@RequestMapping("/member/memberInsert.do")
	public String form(){
		return "member/memberForm";
	}
	
	@RequestMapping(value="/member/memberInsert.do", method=RequestMethod.POST)
	public String process(@ModelAttribute("member") MemberVO member, 
			@RequestPart(value="memImage", required=false) MultipartFile memImage,
			HttpServletRequest req) throws IOException{

		member.setMemImage(memImage);
		
//		2. 검증 : DB스키마에 따른 검증 룰
		Map<String, List<String>> errors = new LinkedHashMap<>();
		req.setAttribute("errors", errors);
		boolean valid = ValidateUtils.validate(member, errors, InsertGroup.class);
		
		String viewName = null;
		String message = null;
		if(valid) {
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
		
		req.setAttribute("message", message);
		
		return viewName;
		
 	}
}














