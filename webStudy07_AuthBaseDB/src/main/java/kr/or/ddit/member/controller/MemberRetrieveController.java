package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SearchVO;

@Controller
@RequestMapping("/member")
public class MemberRetrieveController{
	@Inject
	private MemberService service;
	
	@RequestMapping("memberView.do")
	public String memberView(@RequestParam(name="who", required=true) String memId, Model model) {
		MemberVO member = service.retrieveMember(memId);
		model.addAttribute("member", member);
		return "member/ajax/memberView";
	}
	
	private void makeModel(int currentPage, SearchVO searchVO, Model model) {
		PagingVO<MemberVO> pagingVO = new PagingVO<>(5, 3);
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setSearchVO(searchVO);
		
		List<MemberVO> memberList = service.retrieveMemberList(pagingVO);
		pagingVO.setDataList(memberList);
		
		model.addAttribute("pagingVO", pagingVO);
	}
	
	@RequestMapping("memberList.do")
	public String listView() {
		return "member/memberList";
	}
	
	@RequestMapping(value="memberList.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public PagingVO<MemberVO> list(
			@RequestHeader("accept") String accept,
			@RequestParam(value="page", required=false, defaultValue="1") int currentPage,
			@ModelAttribute("searchVO") SearchVO searchVO,
			Model model) throws ServletException, IOException {
		
		makeModel(currentPage, searchVO, model);
		PagingVO<MemberVO> pagingVO = (PagingVO<MemberVO>) model.asMap().get("pagingVO");
		return pagingVO;
	}
}
















