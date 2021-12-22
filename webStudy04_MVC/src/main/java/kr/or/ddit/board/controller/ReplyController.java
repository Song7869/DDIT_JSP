package kr.or.ddit.board.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import kr.or.ddit.board.service.ReplyService;
import kr.or.ddit.board.service.ReplyServiceImpl;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.resolvers.RequestParam;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ReplyVO;
import lombok.extern.slf4j.Slf4j;

// 
// 
// /reply/replyUpdate.do
// /reply/replyDelete.do
@Slf4j
@Controller
public class ReplyController {

	private ReplyService service = new ReplyServiceImpl();
	
	@RequestMapping(value="/reply/replyList.do", method=RequestMethod.GET)
	public String replyList(
			@RequestParam(value="page", required=false, defaultValue="1") int currentPage,
			@RequestParam("bo_no") int boNo,
			HttpServletRequest req
			) {
		log.info("넘어옴");
		PagingVO<ReplyVO> pagingVO = new PagingVO<>();
		pagingVO.setCurrentPage(currentPage);
		
		ReplyVO detailSearch = new ReplyVO();
		detailSearch.setBoNo(boNo);		
		pagingVO.setDetailSearch(detailSearch);
		
		service.retrieveReplyList(pagingVO);  
		//마샬링
		
		
		log.info("보낼거야");
		return null;
	}
	
	@RequestMapping(value="/reply/replyInsert.do", method=RequestMethod.POST)
	public String replyInsert(
			
			) {
		
		return null;
	}
	
}
