package kr.or.ddit.prod.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.prod.dao.OthersDAO;
import kr.or.ddit.prod.dao.OthersDAOImpl;
import kr.or.ddit.vo.BuyerVO;

@WebServlet({"/others/getLprodList.do", "/others/getBuyerList.do"})
public class OthersRetrieveServlet extends HttpServlet{
	private OthersDAO dao = new OthersDAOImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String uri = req.getRequestURI();
		String contextPath = req.getContextPath();
		uri = uri.substring(contextPath.length());
		if(uri.startsWith("/others/getLprodList.do")) {
			retriveLprodList(req, resp);
		}else if(uri.startsWith("/others/getBuyerList.do")) {
			retriveBuyerList(req, resp);
		}
		
	}
	
	private void retriveBuyerList(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String lprodGu = req.getParameter("lprodGu");
		List<BuyerVO> buyerList = dao.selectBuyerList(lprodGu);
		resp.setContentType("application/json;charset=UTF-8");
		try(
			PrintWriter out = resp.getWriter();	
		){
			ObjectMapper mapper = new ObjectMapper();
			mapper.writeValue(out, buyerList);
		}
	}
	
	private void retriveLprodList(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		List<Map<String, Object>> lprodList = dao.selectLprodList();
		resp.setContentType("application/json;charset=UTF-8");
		try(
			PrintWriter out = resp.getWriter();	
		){
			ObjectMapper mapper = new ObjectMapper();
			mapper.writeValue(out, lprodList);
		}
	}
}













