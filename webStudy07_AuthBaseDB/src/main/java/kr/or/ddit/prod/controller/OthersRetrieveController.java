package kr.or.ddit.prod.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.common.PKNotFoundException;
import kr.or.ddit.prod.dao.OthersDAO;
import kr.or.ddit.vo.BuyerVO;

@RestController
@RequestMapping(value="/others", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
public class OthersRetrieveController{
	@Inject
	private OthersDAO dao;
	
	@RequestMapping(value="getBuyerList.do")
	public List<BuyerVO> retriveBuyerList(
			@RequestParam(value="lprodGu", required=false) String lprodGu){
		if(1==1)
			throw new PKNotFoundException("강제 발생  예외");
		return dao.selectBuyerList(lprodGu);
	}
	
	@RequestMapping(value="getLprodList.do")
	public List<Map<String, Object>> retriveLprodList(){
		return dao.selectLprodList();
	}
}













