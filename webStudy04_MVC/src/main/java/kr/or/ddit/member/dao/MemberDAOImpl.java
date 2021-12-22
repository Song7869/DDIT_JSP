package kr.or.ddit.member.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;

public class MemberDAOImpl implements MemberDAO{

	private SqlSessionFactory sqlSessionFactory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
	

	@Override
	public MemberVO selectMemberForAuth(String memId) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();
		){
//			return sqlSession.selectOne("kr.or.ddit.member.dao.MemberDAO.selsdfasdtMemberForAuth", memId);
//			mapper proxy 생성
			MemberDAO mapper = sqlSession.getMapper(MemberDAO.class);
			return mapper.selectMemberForAuth(memId);
		}
	}

	@Override
	public int insertMember(MemberVO member) {
		// mybatis 에서 insert/update/delete 는 명시적인 트랜잭션 관리가 필요함.
		try(
				SqlSession sqlSession = sqlSessionFactory.openSession();
		){
			MemberDAO mapper = sqlSession.getMapper(MemberDAO.class);
			int cnt = mapper.insertMember(member);
			sqlSession.commit();
			return cnt;
		}
	}

	@Override
	public int selectTotalRecord(PagingVO pagingVO) {
		try(
				SqlSession sqlSession = sqlSessionFactory.openSession();
		){
			MemberDAO mapper = sqlSession.getMapper(MemberDAO.class);
			return mapper.selectTotalRecord(pagingVO);
		}
	}
	
	@Override
	public List<MemberVO> selectMemberList(PagingVO pagingVO) {
		try(
					SqlSession sqlSession = sqlSessionFactory.openSession();
			){
				MemberDAO mapper = sqlSession.getMapper(MemberDAO.class);
				return mapper.selectMemberList(pagingVO);
			}
	}

	@Override
	public MemberVO selectMember(String memId) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();
		){
			MemberDAO mapper = sqlSession.getMapper(MemberDAO.class);
			return mapper.selectMember(memId);
		}
	}

	@Override
	public int updateMember(MemberVO member) {
		try(
				SqlSession sqlSession = sqlSessionFactory.openSession();
		){
			MemberDAO mapper = sqlSession.getMapper(MemberDAO.class);
			int cnt = mapper.updateMember(member);
			sqlSession.commit();
			return cnt;
		}
	}

	@Override
	public int deleteMember(String memId) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();
		){
			MemberDAO mapper = sqlSession.getMapper(MemberDAO.class);
			int cnt = mapper.deleteMember(memId);
			sqlSession.commit();
			return cnt;
		}
	}
}








