package kr.or.ddit.exception;

import java.sql.SQLException;

/**
 * 예외 (Throwable)
 * 	에러 (Error) : 개발자에 의해서 처리가 되지 않는 상황(코드를 바꿔야함), 시스템 fault
 * 
 * 	예외(진짜)(Exception) : 코드상에서 처리가 가능한 예외
 * 
 * 		checked exception (Exception) : 발생가능한 코드는 반드시 예외 처리 코드의 블럭으로 처리하거나 throw로 제어 전달.
 * 			SQLException, IOException
 * 
 * 		unchecked exception (RuntimeException) : 직접 처리하지 않더라도, 호출자에게(최종 JVM에게) 제어권이 전달.
 * 			NullpointerException, IlligalArgumentException, ArithmeticException
 * 
 * 	throws(나는 처리 안함, 내 호출자에게 떠넘길거야// 호출자에게 제어권을 전달), 
 * 	try~catch~finally(직접 예외 처리)
 *
 *
 *
 *
 *	custom exception 정의
 *		:	정의하고 싶은 예외의 종류에 따른 상위 결정.
 *
 */
public class ExceptionDesc {
	public static void main(String[] args) {
		// unchecked exception 은 throw가 없어도, 자동적으로 throw가 된다.
		
		
		//1. checked exception sample
/*		try {
			String retValue = target();
			System.out.println(retValue);
		} catch (SQLException e) {
			e.printStackTrace();
		}*/
		
		//2. unchecked exception sample
		try {
		String retValue2 = targetForUnchecked();
		System.out.println(retValue2);
		}
		catch(RuntimeException e) {
			System.out.println("조용히 처리 끝");
		}
		
		//3. custom exception
		String retValue3 = target();
		System.out.println(retValue3);
		
	}
	
	private static String targetForUnchecked() throws RuntimeException{
		// unchecked exception 은 throw가 없어도, 자동적으로 throw가 된다.
		if(1==1) {
			throw new NullPointerException("강제 발생 예외 입니다.");
		}
		return "targetValueForUnchecked";
	}
	
	
	
	
	private static String target() {
		try {
		if(1==1) {
			throw new SQLException("강제 발생 예외 입니다.");
		}
		return "targetValue";
		}catch(SQLException e){
			System.err.println(e.getMessage());
			throw new CustomException(e);
		}
	}
	
}
