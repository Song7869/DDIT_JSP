package kr.or.ddit.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ReplyVO;

public class ReplyDAOImpl implements ReplyDAO {

	SqlSessionFactory sqlSessionFactory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
	
	@Override
	public int insertReply(ReplyVO reply) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession(true);
		){
			ReplyDAO mapper = sqlSession.getMapper(ReplyDAO.class);
			
			return mapper.insertReply(reply);
		}
		
	}

	@Override
	public int selectTotalRecord(PagingVO<ReplyVO> pagingVO) {
		try(
				SqlSession sqlSession = sqlSessionFactory.openSession();
			){
				ReplyDAO mapper = sqlSession.getMapper(ReplyDAO.class);
				
				return mapper.selectTotalRecord(pagingVO);
			}
	}

	@Override
	public List<ReplyVO> selectReplyList(PagingVO<ReplyVO> pagingVO) {
		try(
				SqlSession sqlSession = sqlSessionFactory.openSession();
			){
				ReplyDAO mapper = sqlSession.getMapper(ReplyDAO.class);
				
				return mapper.selectReplyList(pagingVO);
			}
	}

	@Override
	public int updateReply(ReplyVO reply) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteReply(int repNo) {
		// TODO Auto-generated method stub
		return 0;
	}

}
