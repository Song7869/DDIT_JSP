package kr.or.ddit.employee.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.DepartmentVO;

public class DepartmentDAOImpl implements DepartmentDAO {

	private SqlSessionFactory sqlSessionFactory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
	
	@Override
	public List<DepartmentVO> selectDepartmentList() {
		try(
				SqlSession sqlSession = sqlSessionFactory.openSession();
		){
			DepartmentDAO mapper = sqlSession.getMapper(DepartmentDAO.class);
			return mapper.selectDepartmentList();
		}
	}

}
