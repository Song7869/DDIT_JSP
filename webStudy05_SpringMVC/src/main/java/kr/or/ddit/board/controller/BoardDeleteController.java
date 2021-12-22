package kr.or.ddit.board.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.BoardVO;

@Controller
public class BoardDeleteController {
	@Inject
	private BoardService service;
	
	@RequestMapping(value="/board/boardDelete.do", method=RequestMethod.POST)
	public String delete(
		@RequestParam("boNo") int boNo,
		@RequestParam("boPass") String boPass
		, RedirectAttributes redirectAttributes
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
		redirectAttributes.addFlashAttribute("message", message);
		return viewName;
	}
}
















