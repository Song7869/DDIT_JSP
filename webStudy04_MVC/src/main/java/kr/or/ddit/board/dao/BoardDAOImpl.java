package kr.or.ddit.board.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;

public class BoardDAOImpl implements BoardDAO {

	private SqlSessionFactory sqlSessionFactory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
	
	@Override
	public int insertBoard(BoardVO board, SqlSession sqlSession) {
		return sqlSession.insert("kr.or.ddit.board.dao.BoardDAO.insertBoard", board);
	}

	@Override
	public int selectTotalRecord(PagingVO<BoardVO> pagingVO) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();
		){
			BoardDAO mapper = sqlSession.getMapper(BoardDAO.class);
			return mapper.selectTotalRecord(pagingVO);
		}
	}

	@Override
	public List<BoardVO> selectBoardList(PagingVO<BoardVO> pagingVO) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();
		){
			BoardDAO mapper = sqlSession.getMapper(BoardDAO.class);
			return mapper.selectBoardList(pagingVO);
		}
	}

	@Override
	public BoardVO selectBoard(int boNo) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();
		){
			BoardDAO mapper = sqlSession.getMapper(BoardDAO.class);
			return mapper.selectBoard(boNo);
		}
	}
	
	@Override
	public int incrementCount(Map<String, Object> pMap) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession(true);
		){
			BoardDAO mapper = sqlSession.getMapper(BoardDAO.class);
			return mapper.incrementCount(pMap);
		}
	}

	@Override
	public int updateBoard(BoardVO board, SqlSession sqlSession) {
		return sqlSession.update("kr.or.ddit.board.dao.BoardDAO.updateBoard", board);
	}

	@Override
	public int deleteBoard(int boNo, SqlSession sqlSession) {
		return sqlSession.delete("kr.or.ddit.board.dao.BoardDAO.deleteBoard", boNo);
	}

}















