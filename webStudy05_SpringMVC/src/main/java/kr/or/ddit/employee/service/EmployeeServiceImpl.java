package kr.or.ddit.employee.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.employee.dao.EmployeeDAO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.EmployeeVO;
import kr.or.ddit.vo.PagingVO;
@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Inject
	private EmployeeDAO dao;

	@Override
	public ServiceResult createEmployee(EmployeeVO employee) {
		return null;
	}

	@Override
	public List<EmployeeVO> retrieveEmployeeList(PagingVO<EmployeeVO> pagingVO) {
		int totalRecord = dao.selectTotalRecord(pagingVO);
		List<EmployeeVO> employeeList = dao.selectEmployeeList(pagingVO);
		if(pagingVO!=null) {
			pagingVO.setTotalRecord(totalRecord);
			pagingVO.setDataList(employeeList);
		}
		return employeeList;
	}

	@Override
	public EmployeeVO retrieveEmployee(String empId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceResult modifyEmployee(EmployeeVO employee) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceResult removeEmployee(String empId) {
		// TODO Auto-generated method stub
		return null;
	}

}
