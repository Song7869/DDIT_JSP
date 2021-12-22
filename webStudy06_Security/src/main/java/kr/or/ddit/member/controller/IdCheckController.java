package kr.or.ddit.member.controller;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.common.PKNotFoundException;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.MemberService;

//@Controller
//@ResponseBody
@RestController
@RequestMapping(value="/member", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
public class IdCheckController {
	
	@Inject
	private MemberService service;

	@RequestMapping("idCheck")
	public Map<String, Object> idCheck(@RequestParam(required=true) String memId) {
		ServiceResult result = null;
		String message = null;
		try {
			service.retrieveMember(memId);
			result = ServiceResult.PKDUPLICATED;
			message = "중복된 아이디임.";
		}catch (PKNotFoundException e) {
			result = ServiceResult.OK;
			message = "사용 가능한 아이디임.";
		}
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("result", result);
		resultMap.put("message", message);
		return resultMap;	
	}
}

















