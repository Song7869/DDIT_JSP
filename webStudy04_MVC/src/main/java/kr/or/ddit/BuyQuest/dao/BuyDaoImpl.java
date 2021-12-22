package kr.or.ddit.BuyQuest.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.BuyQuestVO;

public class BuyDaoImpl implements BuyDao{
	
	private SqlSessionFactory sqlSessionFactory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory(); 
	
	@Override
	public List<BuyQuestVO> selectBQList() {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();
			)
		{
			BuyDao bd = sqlSession.getMapper(BuyDao.class);
			return bd.selectBQList();
		}
		
	}

}
