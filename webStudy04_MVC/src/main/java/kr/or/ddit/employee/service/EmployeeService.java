package kr.or.ddit.employee.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.EmployeeVO;
import kr.or.ddit.vo.PagingVO;

public interface EmployeeService {
	public ServiceResult createEmployee(EmployeeVO employee);
	public List<EmployeeVO> retrieveEmployeeList(PagingVO<EmployeeVO> pagingVO);
	public EmployeeVO retrieveEmployee(String empId);
	public ServiceResult modifyEmployee(EmployeeVO employee);
	public ServiceResult removeEmployee(String empId);
}
