package kr.or.ddit.board.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SearchVO;

@Controller
@RequestMapping("/board")
public class BoardRetrieveController {
	@Inject
	private BoardService service;
	
	@RequestMapping("boardView.do")
	public String boardView(
		@RequestParam("what") int boNo
		, Model model
	) {
		BoardVO board = service.retrieveBoard(boNo);
		
		model.addAttribute("board", board);
		
		return "board/boardView";
	}
	
	@RequestMapping("boardList.do")
	public String list(
		@RequestParam(value="page", required=false, defaultValue="1") int currentPage
		, @ModelAttribute("searchVO") SearchVO searchVO
		, Model model
	) {
		
		PagingVO<BoardVO> pagingVO = new PagingVO<>();
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setSearchVO(searchVO);
		
		service.retrieveBoardList(pagingVO);
		
		model.addAttribute("pagingVO", pagingVO);
		
		return "board/boardList";
	}
}






