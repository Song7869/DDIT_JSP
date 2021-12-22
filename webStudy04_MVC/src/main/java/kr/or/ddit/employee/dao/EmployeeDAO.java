package kr.or.ddit.employee.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.or.ddit.vo.EmployeeVO;
import kr.or.ddit.vo.PagingVO;

public interface EmployeeDAO {
	public int insertEmployee(EmployeeVO employee);
	public int selectTotalRecord(PagingVO<EmployeeVO> pagingVO);
	public List<EmployeeVO> selectEmployeeList(@Param("pagingVO") PagingVO<EmployeeVO> pagingVO);
	public EmployeeVO selectEmployee(String empId);
	public int updateEmployee(EmployeeVO employee);
	public int deleteEmployee(String empId);
}
