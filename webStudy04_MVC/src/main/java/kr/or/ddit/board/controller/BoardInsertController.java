package kr.or.ddit.board.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.resolvers.ModelAttribute;
import kr.or.ddit.mvc.annotation.resolvers.RequestPart;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.mvc.fileupload.MultipartFile;
import kr.or.ddit.util.ValidateUtils;
import kr.or.ddit.validate.groups.InsertGroup;
import kr.or.ddit.vo.BoardVO;

@Controller
public class BoardInsertController {
	
	private BoardService service = new BoardServiceImpl();

	@RequestMapping("/board/boardInsert.do")
	public String getController(@ModelAttribute("board") BoardVO board ) {
		return "board/boardForm";
	}
	
	@RequestMapping(value="/board/boardInsert.do", method=RequestMethod.POST)
	public String postController(
		@ModelAttribute("board") BoardVO board,
		@RequestPart(value="boFiles", required=false) MultipartFile[] boFiles
		, HttpServletRequest req
	) {
		board.setBoFiles(boFiles);	// 첨부파일
		Map<String, List<String>> errors = new LinkedHashMap<>();
		req.setAttribute("errors", errors);
		boolean valid = ValidateUtils.validate(board, errors, InsertGroup.class); // 인서트 그룹 검증
		
		String viewName = null;
		String message = null;
		if(valid) {
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
		
		req.setAttribute("message", message);
		
		return viewName;
	}
}
