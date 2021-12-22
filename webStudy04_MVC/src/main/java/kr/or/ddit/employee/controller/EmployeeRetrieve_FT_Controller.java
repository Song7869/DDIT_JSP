package kr.or.ddit.employee.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.employee.dao.DepartmentDAO;
import kr.or.ddit.employee.dao.DepartmentDAOImpl;
import kr.or.ddit.employee.service.EmployeeService;
import kr.or.ddit.employee.service.EmployeeServiceImpl;
import kr.or.ddit.mvc.annotation.resolvers.RequestHeader;
import kr.or.ddit.mvc.annotation.resolvers.RequestParam;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.vo.DepartmentVO;
import kr.or.ddit.vo.DepartmentWrapper;
import kr.or.ddit.vo.EmployeeVO;
import kr.or.ddit.vo.EmployeeWrapper;
import kr.or.ddit.vo.FancyTreeNode;
import kr.or.ddit.vo.PagingVO;

@Controller
public class EmployeeRetrieve_FT_Controller {
	
	private EmployeeService service = new EmployeeServiceImpl();
	
	private DepartmentDAO deptDAO = new DepartmentDAOImpl();
	
	@RequestMapping("/emp/deptList.do")
	public String listDept(@RequestParam(value="dept", required=false, defaultValue="0") Integer dept
			, HttpServletResponse resp) throws IOException {
		
		List<DepartmentVO> deptList = deptDAO.selectDepartmentList();
		List<FancyTreeNode> nodeList = new ArrayList<>(deptList.size());
		for( DepartmentVO tmp : deptList) {
			nodeList.add(new DepartmentWrapper(tmp));
		}
		resp.setContentType("application/json;charset=UTF-8");
		try(
			PrintWriter out = resp.getWriter();	
		){
			ObjectMapper mapper = new ObjectMapper();
			mapper.writeValue(out, nodeList);
		}
		return null;
	}
	
	@RequestMapping("/emp/employeeList_FT.do")
	public String listFT(@RequestParam(value="manager", required=false, defaultValue="0") Integer manager
			, HttpServletRequest req
			, HttpServletResponse resp
			, @RequestHeader("accept") String accept) throws IOException {
		// manager 라는 옵셔널 파라미터를 통해 해당 직원의 부하직원 목록을 조회.
		PagingVO<EmployeeVO> pagingVO = new PagingVO<>();
		EmployeeVO vo = new EmployeeVO();
		vo.setManagerId(manager);
		pagingVO.setDetailSearch(vo);
		
		List<EmployeeVO> empList = service.retrieveEmployeeList(pagingVO);
		
		List<FancyTreeNode> nodeList = new ArrayList<>(empList.size());
		for(EmployeeVO emp : empList) {
			nodeList.add(new EmployeeWrapper(emp));
		}
		
		req.setAttribute("empList", empList);
		if(StringUtils.containsIgnoreCase(accept, "json")) {
			resp.setContentType("application/json;charset=UTF-8");
			try(
				PrintWriter out = resp.getWriter();	
			){
				ObjectMapper mapper = new ObjectMapper();
				mapper.writeValue(out, nodeList);
			}
			return null;
		}else {
			return "employee/employeeList_FT";
		}
	}
}















