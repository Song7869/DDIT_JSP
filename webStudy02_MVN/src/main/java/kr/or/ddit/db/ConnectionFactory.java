package kr.or.ddit.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.w3c.dom.ranges.RangeException;

import oracle.jdbc.pool.OracleConnectionPoolDataSource;

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
	private static DataSource dataSource;
	
	//코드블럭이라 1번만 실행된다.
	static {
		//properties : 컬렉션 (외부의 데이터까지 관리 가능 .properties)
		//resource bundle : 메세지에 locale(언어/지역)을 설정 가능하다.
		
		ResourceBundle bundle = ResourceBundle.getBundle("kr.or.ddit.db.dbInfo", Locale.ENGLISH);
		url = bundle.getString("url");
		user = bundle.getString("user");
		password = bundle.getString("password");
//		String message = bundle.getString("message");
		int initialSize = Integer.parseInt(bundle.getString("initialSize"));
		int maxTotal = Integer.parseInt(bundle.getString("maxWait"));
		
		BasicDataSource ds = new BasicDataSource();
		//DB에 종속되지 않는 pooling 방법
		dataSource = ds;
		ds.setDriverClassName(bundle.getString("driverClassName"));
		ds.setUrl(url);
		ds.setUsername(user);
		ds.setPassword(password);
		ds.setMaxTotal(maxTotal);
		ds.setMaxWaitMillis(initialSize);
		
		// 미리 처음에 3개를 만들어 놓는다.
		ds.setInitialSize(3);
		ds.setMaxWaitMillis(2000);
		ds.setMaxTotal(5);
		//서버의 부하가 일정하게 유지된다. --> 클라이언트 입장에서는 속도가 향상된 것으로 느껴질 것이다.
		
//		OracleConnectionPoolDataSource ds;
//		try {
//			ds = new OracleConnectionPoolDataSource();
//			dataSource = ds;
//			ds.setURL(url);
//			ds.setUser(user);
//			ds.setPassword(password);
//		} catch (SQLException e) {
//			throw new RuntimeException(e);
//		}

//		try {
//			Class.forName(bundle.getString("driverClassName"));
//			System.out.println(message);
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException(e);//체크드 익셉션을 언체크드로 바꾸는 과정 -> 이렇게 해야 에러메시지를 내보낼 수 있음
//		}		
	}
	
	public static Connection getConnection() throws SQLException{

//		return DriverManager.getConnection(url, user, password);
		return dataSource.getConnection();
	}
	
}
