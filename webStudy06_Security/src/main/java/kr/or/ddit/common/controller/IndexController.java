package kr.or.ddit.common.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController{
	@RequestMapping("/index.do")
	public String index(HttpServletRequest req, HttpServletResponse resp){
		return "index";
	}
}

// 400 ~404, 500 에러페이지 설정(web.xml error-page)
// datasource-context에서 vo수집 패키지 별도 설정 그리고 별도 패키지에서 중복발생시 -> vo 설정 @Alias("")
// 
