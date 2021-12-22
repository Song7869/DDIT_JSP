package kr.or.ddit.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Factory Object[Method] Pattern
 * 	: consumer 에 의해 소비되는 객체 생성만을 전담하는 객체 운영.
 * @author pc12
 *
 */
public class ConnectionFactory {
	private static String url;
	private static String user;
	private static String password;
	
	//코드블럭이라 1번만 실행된다.
	static {
		//properties : 컬렉션 (외부의 데이터까지 관리 가능 .properties)
		//resource bundle : 메세지에 locale(언어/지역)을 설정 가능하다.
		
		ResourceBundle bundle = ResourceBundle.getBundle("kr.or.ddit.db.dbInfo", Locale.ENGLISH);
		url = bundle.getString("url");
		user = bundle.getString("user");
		password = bundle.getString("password");
		String message = bundle.getString("message");
		
		try {
			Class.forName(bundle.getString("driverClassName"));
			System.out.println(message);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);//체크드 익셉션을 언체크드로 바꾸는 과정 -> 이렇게 해야 에러메시지를 내보낼 수 있음
		}		
	}
	
	public static Connection getConnection() throws SQLException{

		return DriverManager.getConnection(url, user, password);
	}
	
}
