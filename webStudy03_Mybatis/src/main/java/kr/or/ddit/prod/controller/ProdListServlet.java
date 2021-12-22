package kr.or.ddit.prod.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.prod.dao.OthersDAO;
import kr.or.ddit.prod.dao.OthersDAOImpl;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;
import kr.or.ddit.vo.SearchVO;

/**
 * 검색 조건 : 상품명, 거래처명, 분류명 세가지 검색 타입으로 검색 가능.
 * 	1. 세가지 검색 조건 중 하나만을 사용해서 검색.
 *     동기요청 
 *  2. 두가지 이상의 검색 조건을 동시 사용.
 *     비동기 요청   
 *
 */
@WebServlet("/prod/prodList.do")
public class ProdListServlet extends HttpServlet{
	private ProdService service = new ProdServiceImpl();
	private OthersDAO othersDAO = new OthersDAOImpl();
	
	private void addAttribute(HttpServletRequest req) {
		req.setAttribute("lprodList", othersDAO.selectLprodList());
		req.setAttribute("buyerList", othersDAO.selectBuyerList(null));
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		String accept = req.getHeader("Accept");
		
		addAttribute(req);
		
		int currentPage = 1;
 		String pageParam = req.getParameter("page");
 		if(StringUtils.isNumeric(pageParam)) {
 			currentPage = Integer.parseInt(pageParam);
 		}
 		
 		ProdVO detailSearch = new ProdVO();
 		try {
			BeanUtils.populate(detailSearch, req.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
 		
 		PagingVO<ProdVO> pagingVO = new PagingVO<>();
 		pagingVO.setCurrentPage(currentPage);
 		pagingVO.setDetailSearch(detailSearch);
 		
 		service.retrieveProdList(pagingVO);
 		
 		if(StringUtils.containsIgnoreCase(accept, "json")) {
 			resp.setContentType("application/json;charset=UTF-8");
 			try(
 				PrintWriter out = resp.getWriter();	
 			){
 				ObjectMapper mapper = new ObjectMapper();
 				mapper.writeValue(out, pagingVO);
 			}
 		}else {
 			req.setAttribute("pagingVO", pagingVO);
 			
 			String viewName = "prod/prodList";
 			String prefix = "/WEB-INF/views/";
 			String suffix = ".jsp";
 			req.getRequestDispatcher(prefix+viewName+suffix).forward(req, resp);
 		}
 		
	}
}

















