package kr.or.ddit.employee.dao;

import java.util.List;

import kr.or.ddit.vo.DepartmentVO;

public interface DepartmentDAO {
	public List<DepartmentVO> selectDepartmentList();
}
