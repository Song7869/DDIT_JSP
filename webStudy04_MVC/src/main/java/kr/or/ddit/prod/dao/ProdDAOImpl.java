package kr.or.ddit.prod.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;

public class ProdDAOImpl implements ProdDAO {
	private SqlSessionFactory sqlSessionFactory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory();

	@Override
	public int insertProd(ProdVO prod) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();
		){
			ProdDAO mapper = sqlSession.getMapper(ProdDAO.class);
			int cnt = mapper.insertProd(prod);
			sqlSession.commit();
			return cnt;
		}
	}

	@Override
	public int selectTotalRecord(PagingVO<ProdVO> pagingVO) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();
		){
			ProdDAO mapper = sqlSession.getMapper(ProdDAO.class);
			return mapper.selectTotalRecord(pagingVO);
		}
	}

	@Override
	public List<ProdVO> selectProdList(PagingVO<ProdVO> pagingVO) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();
		){
			ProdDAO mapper = sqlSession.getMapper(ProdDAO.class);
			return mapper.selectProdList(pagingVO);
		}
	}

	@Override
	public ProdVO selectProd(String prodId) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();
		){
			ProdDAO mapper = sqlSession.getMapper(ProdDAO.class);
			return mapper.selectProd(prodId);
		}
	}

	@Override
	public int updateProd(ProdVO prod) {
		try(
				SqlSession sqlSession = sqlSessionFactory.openSession();
			){
				ProdDAO mapper = sqlSession.getMapper(ProdDAO.class);
				int cnt = mapper.updateProd(prod);
				sqlSession.commit();
				return cnt;
			}
	}

}














