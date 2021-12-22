package kr.or.ddit.sample.dao;

import org.springframework.stereotype.Repository;

@Repository
public class SampleDAO_Mysql implements SampleDAO {
	
	public SampleDAO_Mysql() {
		System.out.println(getClass().getSimpleName()+" 객체 생성");
	}

	public void init() {
		System.out.println(getClass().getSimpleName()+" 초기화 종료");
	}
	
	public void destroy() {
		System.out.println(getClass().getSimpleName()+" 소멸");
	}
	
	@Override
	public String selectSampleData(String pk) {
		return "Mysql 에서 조회된 데이터 PK="+pk;
	}

}
