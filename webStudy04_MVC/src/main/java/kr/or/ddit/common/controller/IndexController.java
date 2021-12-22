package kr.or.ddit.common.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;

@Controller
public class IndexController{
	@RequestMapping("/index.do")
	public String index(HttpServletRequest req, HttpServletResponse resp){
		return "template";
	}
}
