package kr.or.ddit.mybatis;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * Singleton 형태의 SqlSessionFactory를 생성하기 위한 클래스
 *
 */
public class CustomSqlSessionFactoryBuilder {
	private static SqlSessionFactory sqlSessionFactory;
	static {
		String xmlPath = "kr/or/ddit/mybatis/Configuration.xml";
		try(
			Reader reader = Resources.getResourceAsReader(xmlPath);
		){
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}
}









