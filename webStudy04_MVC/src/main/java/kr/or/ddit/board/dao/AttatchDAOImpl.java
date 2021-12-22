package kr.or.ddit.board.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.AttatchVO;
import kr.or.ddit.vo.BoardVO;

public class AttatchDAOImpl implements AttatchDAO {

	private SqlSessionFactory sqlSessionFactory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
	
	@Override
	public int insertAttatches(BoardVO board, SqlSession sqlSession) {
		return sqlSession.insert("kr.or.ddit.board.dao.AttatchDAO.insertAttatches", board);
	}

	@Override
	public AttatchVO selectAttatch(int attNo) { // 트랜젝션이 없다.(서비스 1개의 일로, 1개의 DB 수정이 발생함)
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();	
		){
			AttatchDAO mapper = sqlSession.getMapper(AttatchDAO.class);
			return mapper.selectAttatch(attNo);
		}
	}

	@Override
	public int deleteAttatches(BoardVO board, SqlSession sqlSession) { // 트랜젝션이 발생(서비스 1개의 일로 여러개의 DB수정이 발생함)
		return sqlSession.delete("kr.or.ddit.board.dao.AttatchDAO.deleteAttatches", board);
	}

	@Override
	public int upDowncount(int attNo) {
		try(
			SqlSession sqlsession = sqlSessionFactory.openSession(true);
		){
			AttatchDAO mapper = sqlsession.getMapper(AttatchDAO.class);
			return mapper.upDowncount(attNo);
		}
	}

}












