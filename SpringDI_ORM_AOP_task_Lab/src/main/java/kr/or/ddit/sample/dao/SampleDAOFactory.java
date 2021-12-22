package kr.or.ddit.sample.dao;

public class SampleDAOFactory {
	public static SampleDAO getSampleDAO() {
//		return new SampleDAO_Mysql();
		return new SampleDAO_Oracle();
	}
}
