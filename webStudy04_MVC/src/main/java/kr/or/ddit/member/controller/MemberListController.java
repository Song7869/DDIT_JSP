package kr.or.ddit.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.annotation.resolvers.ModelAttribute;
import kr.or.ddit.mvc.annotation.resolvers.RequestHeader;
import kr.or.ddit.mvc.annotation.resolvers.RequestParam;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SearchVO;

@Controller
public class MemberListController{
	private MemberService service = new MemberServiceImpl();
	
	private void makeModel(int currentPage, SearchVO searchVO, HttpServletRequest req) {
		PagingVO<MemberVO> pagingVO = new PagingVO<>(5, 3);
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setSearchVO(searchVO);
		
		List<MemberVO> memberList = service.retrieveMemberList(pagingVO);
		pagingVO.setDataList(memberList);
		
		req.setAttribute("pagingVO", pagingVO);
	}
	
	@RequestMapping("/member/memberList.do")
	public String list(
			@RequestHeader("accept") String accept,
			@RequestParam(value="page", required=false, defaultValue="1") int currentPage,
			@ModelAttribute("searchVO") SearchVO searchVO,
			HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		makeModel(currentPage, searchVO, req);
		
		String viewName = null;
		if(accept.contains("json")) {
			resp.setContentType("application/json;charset=UTF-8");
			PagingVO<MemberVO> pagingVO = (PagingVO) req.getAttribute("pagingVO");
			try(
				PrintWriter out = resp.getWriter();	
			){
				ObjectMapper mapper = new ObjectMapper();
				mapper.writeValue(out, pagingVO);
			}
		}else {
			viewName = "member/memberList";
		}
		return viewName;
	}
}
















