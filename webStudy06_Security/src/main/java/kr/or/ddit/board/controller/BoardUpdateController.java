package kr.or.ddit.board.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.validate.groups.UpdateGroup;
import kr.or.ddit.vo.BoardVO;

@Controller
@RequestMapping("/board/boardUpdate.do")
public class BoardUpdateController {
	@Inject
	private BoardService service;

	@RequestMapping
	public String getController(@RequestParam("what") int boNo, Model model) {
		BoardVO board = service.retrieveBoard(boNo);
		model.addAttribute("board", board);
		return "board/boardEdit";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String postController(
		@Validated(UpdateGroup.class) @ModelAttribute("board") BoardVO board,
		Errors errors
		, Model model
	) {
		String viewName = null;
		String message = null;
		if(!errors.hasErrors()) {
			ServiceResult result = service.modifyBoard(board);
			switch(result) {
			case INVALIDPASSWORD:
				viewName = "board/boardEdit";
				message = "비번 오류";
				break;
			case OK:
				viewName = "redirect:/board/boardView.do?what="+board.getBoNo();
				break;
			default:
				viewName = "board/boardEdit";
				message = "서버 오류, 잠시뒤 다시 해보셈.";
			}
			
		}else {
			viewName = "board/boardEdit";
			
		}
		
		model.addAttribute("message", message);
		
		return viewName;
	}
}
