package kr.or.ddit.board.controller;

import javax.servlet.http.HttpServletRequest;

import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.mvc.annotation.resolvers.ModelAttribute;
import kr.or.ddit.mvc.annotation.resolvers.RequestParam;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SearchVO;

@Controller
public class BoardRetrieveController {
	private BoardService service = new BoardServiceImpl();
	
	@RequestMapping("/board/boardView.do")
	public String boardView(
		@RequestParam("what") int boNo
		, HttpServletRequest req
	) {
		BoardVO board = service.retrieveBoard(boNo);
		
		req.setAttribute("board", board);
		
		return "board/boardView";
	}
	
	@RequestMapping("/board/boardList.do")
	public String list(
		@RequestParam(value="page", required=false, defaultValue="1") int currentPage
		, @ModelAttribute("searchVO") SearchVO searchVO
		, HttpServletRequest req
	) {
		
		PagingVO<BoardVO> pagingVO = new PagingVO<>();
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setSearchVO(searchVO);
		
		service.retrieveBoardList(pagingVO);
		
		req.setAttribute("pagingVO", pagingVO);
		
		return "board/boardList";
	}
}






