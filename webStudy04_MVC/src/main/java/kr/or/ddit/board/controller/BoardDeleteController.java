package kr.or.ddit.board.controller;

import javax.servlet.http.HttpSession;

import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.resolvers.RequestParam;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.vo.BoardVO;

@Controller
public class BoardDeleteController {
	private BoardService service = new BoardServiceImpl();
	
	@RequestMapping(value="/board/boardDelete.do", method=RequestMethod.POST)
	public String delete(
		@RequestParam("boNo") int boNo,
		@RequestParam("boPass") String boPass
		, HttpSession session
	) {
		BoardVO board = new BoardVO();
		board.setBoNo(boNo);
		board.setBoPass(boPass);
		ServiceResult result = service.removeBoard(board);
		String viewName = null;
		String message = null;
		switch (result) {
		case INVALIDPASSWORD:
			viewName = "redirect:/board/boardView.do?what="+boNo;
			message = "비번 오류";
			break;
		case OK:
			viewName = "redirect:/board/boardList.do";
			break;

		default:
			viewName = "redirect:/board/boardView.do?what="+boNo;
			message = "서버 오류";
			break;
		}
		session.setAttribute("message", message);
		return viewName;
	}
}
















