package kr.or.ddit.board.controller;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.board.dao.BoardDAO;
import kr.or.ddit.enumpkg.ServiceResult;

@RestController
@RequestMapping("/board")
public class RepAndRecController {
	@Inject
	private BoardDAO dao;
	
	@RequestMapping(value="recommend.do", produces=MediaType.TEXT_PLAIN_VALUE)
	public String recommend(@RequestParam("what") int boNo){
		Map<String, Object> pMap = new HashMap<>();
		pMap.put("boNo", boNo);
		pMap.put("incType", "BO_REC");
		int cnt = dao.incrementCount(pMap);
		ServiceResult result = null;
		if(cnt>0) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		return result.name();
	}
	
	@RequestMapping(value="report.do", produces=MediaType.TEXT_PLAIN_VALUE)
	public String report(@RequestParam("what") int boNo){
		Map<String, Object> pMap = new HashMap<>();
		pMap.put("boNo", boNo);
		pMap.put("incType", "BO_REP");
		int cnt = dao.incrementCount(pMap);
		ServiceResult result = null;
		if(cnt>0) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		return result.name();
	}
}













