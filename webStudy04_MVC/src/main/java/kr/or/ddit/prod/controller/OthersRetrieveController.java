package kr.or.ddit.prod.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.mvc.annotation.resolvers.RequestParam;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.prod.dao.OthersDAO;
import kr.or.ddit.prod.dao.OthersDAOImpl;
import kr.or.ddit.vo.BuyerVO;

@Controller
public class OthersRetrieveController{
	private OthersDAO dao = new OthersDAOImpl();
	
	@RequestMapping("/others/getBuyerList.do")
	public String retriveBuyerList(
			@RequestParam(value="lprodGu", required=false) String lprodGu,
			HttpServletResponse resp) throws IOException, ServletException {
		List<BuyerVO> buyerList = dao.selectBuyerList(lprodGu);
		resp.setContentType("application/json;charset=UTF-8");
		try(
			PrintWriter out = resp.getWriter();	
		){
			ObjectMapper mapper = new ObjectMapper();
			mapper.writeValue(out, buyerList);
		}
		return null;
	}
	
	@RequestMapping("/others/getLprodList.do")
	public String retriveLprodList(HttpServletResponse resp) throws IOException, ServletException {
		List<Map<String, Object>> lprodList = dao.selectLprodList();
		resp.setContentType("application/json;charset=UTF-8");
		try(
			PrintWriter out = resp.getWriter();	
		){
			ObjectMapper mapper = new ObjectMapper();
			mapper.writeValue(out, lprodList);
		}
		return null;
	}
}













