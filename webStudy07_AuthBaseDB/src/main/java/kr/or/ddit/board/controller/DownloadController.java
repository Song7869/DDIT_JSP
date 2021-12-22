package kr.or.ddit.board.controller;

import java.io.File;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.vo.AttatchVO;

@Controller
public class DownloadController {
	
	@Inject
	private BoardService service;
	
	@RequestMapping("/board/download.do")
	public String download(@RequestParam("what") int attNo, Model model) {
		AttatchVO atch = service.download(attNo);
		model.addAttribute("atch", atch);
		return "downloadView";
	}
}










