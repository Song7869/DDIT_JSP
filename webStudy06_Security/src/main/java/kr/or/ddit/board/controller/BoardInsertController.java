package kr.or.ddit.board.controller;

import javax.inject.Inject;

import org.springframework.aop.support.AopUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.validate.groups.InsertGroup;
import kr.or.ddit.vo.BoardVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/board/boardInsert.do")
public class BoardInsertController {
	
	private BoardService service;
	
	@Inject
	public void setService(BoardService service) {
		this.service = service;
		log.info("주입된 target : {}, proxy여부 : {} ",service.getClass().getName(), AopUtils.isAopProxy(service));
	}

	@RequestMapping
	public String getController(@ModelAttribute("board") BoardVO board ) {
		return "board/boardForm";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String postController(
		@Validated(InsertGroup.class) @ModelAttribute("board") BoardVO board
		, Errors errors
		, Model model
	) {
		String viewName = null;
		String message = null;
		if(!errors.hasErrors()) {
			ServiceResult result = service.createBoard(board);
			switch(result) {
			case OK:
				viewName = "redirect:/board/boardView.do?what="+board.getBoNo();
				break;
			default:
				viewName = "board/boardForm";
				message = "서버 오류, 잠시뒤 다시 해보셈.";
			}
			
		}else {
			viewName = "board/boardForm";
			
		}
		
		model.addAttribute("message", message);
		
		return viewName;
	}
}
