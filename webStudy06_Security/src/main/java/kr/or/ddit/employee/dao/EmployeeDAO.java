package kr.or.ddit.employee.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.EmployeeVO;
import kr.or.ddit.vo.PagingVO;
@Repository
public interface EmployeeDAO {
	public int insertEmployee(EmployeeVO employee);
	public int selectTotalRecord(PagingVO<EmployeeVO> pagingVO);
	public List<EmployeeVO> selectEmployeeList(@Param("pagingVO") PagingVO<EmployeeVO> pagingVO);
	public EmployeeVO selectEmployee(String empId);
	public int updateEmployee(EmployeeVO employee);
	public int deleteEmployee(String empId);
}
