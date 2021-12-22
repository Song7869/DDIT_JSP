package kr.or.ddit.board.controller;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.board.service.ReplyService;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.validate.groups.UpdateGroup;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ReplyVO;

// /reply/replyList.do
// /reply/replyInsert.do
// /reply/replyUpdate.do
// /reply/replyDelete.do
@RestController
@RequestMapping("/reply")
public class ReplyController {
	@Inject
	private ReplyService service;
	
	@RequestMapping(value="replyInsert.do", method=RequestMethod.POST)
	public Map<String, Object> replyInsert(@ModelAttribute("reply") ReplyVO reply){
		Map<String, Object> resultMap = new HashMap<>();
		ServiceResult result = service.createReply(reply);
		if(ServiceResult.FAILED.equals(result)) {
			resultMap.put("message", "서버 오류");
		}
		resultMap.put("result", result.name());
		resultMap.put("reply", reply);
		
		return resultMap;
	}
	
	@RequestMapping("replyList.do")
	public PagingVO<ReplyVO> replyList(
		@RequestParam("boNo") int boNo 
		, @RequestParam(value="page", required=false, defaultValue="1") int currentPage
	){
		PagingVO<ReplyVO> pagingVO = new PagingVO<>(5, 5);
		pagingVO.setCurrentPage(currentPage);
		
		ReplyVO detailSearch = new ReplyVO();
		detailSearch.setBoNo(boNo);
		pagingVO.setDetailSearch(detailSearch);
		
		service.retrieveReplyList(pagingVO);
		
		return pagingVO;
	}
	
	@RequestMapping("replyUpdate.do")
	public Map<String, Object> update(
		@Validated(UpdateGroup.class) @ModelAttribute("reply") ReplyVO reply,
		BindingResult errors
	) {
		ServiceResult result = null;
		Map<String, Object> resultMap = new HashMap<>();
		if(!errors.hasErrors()) {
			result = service.modifyReply(reply);
			if(ServiceResult.INVALIDPASSWORD.equals(result)) {
				resultMap.put("message", "비밀번호 오류");
			}
		}else {
			result = ServiceResult.FAILED;
			resultMap.put("message", "검증에 걸렸음.");
		}
		resultMap.put("result", result);
		return resultMap;
	}
	
	@RequestMapping("replyDelete.do")
	public Map<String, Object> delete(@ModelAttribute("reply") ReplyVO reply) {
		ServiceResult result = service.removeReply(reply);
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("result", result);
		if(ServiceResult.INVALIDPASSWORD.equals(result)) {
			resultMap.put("message", "비밀번호 오류");
		}
		return resultMap;
	}
	
}
