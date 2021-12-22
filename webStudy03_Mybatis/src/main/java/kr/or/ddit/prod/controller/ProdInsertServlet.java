package kr.or.ddit.prod.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.prod.dao.OthersDAO;
import kr.or.ddit.prod.dao.OthersDAOImpl;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.util.ValidateUtils;
import kr.or.ddit.validate.groups.InsertGroup;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.ProdVO;

@WebServlet("/prod/prodInsert.do")
public class ProdInsertServlet extends HttpServlet{
	private ProdService service = new ProdServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String viewName = "prod/prodForm";
		
		String prefix = "/WEB-INF/views/";
		String suffix = ".jsp";
		req.getRequestDispatcher(prefix+viewName+suffix).forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		ProdVO prod = new ProdVO();
		req.setAttribute("prod", prod);
		
		Map<String, String[]> paramterMap = req.getParameterMap();
		try {
			BeanUtils.populate(prod, paramterMap);
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new ServletException(e);
		}
		Map<String, List<String>> errors = new LinkedHashMap<>();
		req.setAttribute("errors", errors);
		boolean valid = ValidateUtils.validate(prod, errors, InsertGroup.class);
		
		String viewName = null;
		String message = null;
		if(valid) {
			ServiceResult result = service.createProd(prod);
			switch(result) {
			case OK:
				viewName = "redirect:/prod/prodView.do?what="+prod.getProdId();
				break;
			default:
				viewName = "prod/prodForm";
				message = "서버 오류, 잠시뒤 다시 해보셈.";
			}
			
		}else {
//		3-2. 불통
			viewName = "prod/prodForm";
			
		}
		
		req.setAttribute("message", message);
		
		if(viewName.startsWith("redirect:")) {
			viewName = viewName.substring("redirect:".length());
			resp.sendRedirect(req.getContextPath() + viewName);
		}else {
			String prefix = "/WEB-INF/views/";
			String suffix = ".jsp";
			req.getRequestDispatcher(prefix+viewName+suffix).forward(req, resp);
		}
		
	}
}
