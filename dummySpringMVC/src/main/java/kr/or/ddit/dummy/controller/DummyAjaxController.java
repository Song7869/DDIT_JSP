package kr.or.ddit.dummy.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//@Controller
@RequestMapping("/dummy/ajax")
//@ResponseBody
@RestController
public class DummyAjaxController {
	
	@RequestMapping(produces="text/plain;charset=UTF-8")
	public String makeText() {
		return "응답 메시지";
	}
	
	@RequestMapping(produces="application/json;charset=UTF-8")
	public Map<String, Object> makeJson() {
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("result", "success");
		resultMap.put("message", "성공");
		
		//json : 마샬링 -> 직렬화
		
		return resultMap;
	}
}
