package kr.or.ddit.employee.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.employee.service.EmployeeService;
import kr.or.ddit.employee.service.EmployeeServiceImpl;
import kr.or.ddit.mvc.annotation.resolvers.RequestHeader;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.vo.EmployeeVO;
import kr.or.ddit.vo.PagingVO;

@Controller
public class EmployeeRetrieveController {
	private EmployeeService service = new EmployeeServiceImpl();
	
	@RequestMapping("/emp/employeeList.do")
	public String list(
		HttpServletRequest req
		, @RequestHeader("accept") String accept
		, HttpServletResponse resp
	)throws ServletException, IOException{
		PagingVO<EmployeeVO> pagingVO = new PagingVO<>();
//		pagingVO.setCurrentPage(currentPage);
		
		List<EmployeeVO> empList = service.retrieveEmployeeList(pagingVO);
	
		if(StringUtils.containsIgnoreCase(accept, "json")) {
			resp.setContentType("application/json;charset=UTF-8");
			try(
				PrintWriter out = resp.getWriter();	
			){
				ObjectMapper mapper = new ObjectMapper();
				mapper.writeValue(out, pagingVO);
			}
			return null;
		}else {
			req.setAttribute("pagingVO", pagingVO);
			return "employee/employeeList";
		}
	}
}














