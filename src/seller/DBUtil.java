package seller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//JAVA DOC 주석
/**
 * 
 * @author 장정우
 * 오라클 연결 클래스입니다.
 *
 */
public class DBUtil {
	
	private Connection conn = null;
	
	/**
	 * 서버에 연결합니다.
	 * @return 연결 객체를 반환합니다.
	 */
	public Connection open() {

	      Connection conn = null;
	      String url = "jdbc:oracle:thin:@ssangsinsa.c7oset28told.ap-northeast-2.rds.amazonaws.com:1521:orcl";
	      String id = "ADMIN";
	      String pw = "SS10231110";

	      try {
	         // lib 추가해줘야 해요
	         Class.forName("oracle.jdbc.driver.OracleDriver");
	         conn = DriverManager.getConnection(url, id, pw);

	         return conn;

	      } catch (Exception e) {
	         System.out.println("DBUtil.getConnection()");
	         e.printStackTrace();
	      }

	      return null;

	   }//open
	
	/**
	 * 연결을 종료합니다.
	 */
	public void close() {
		
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}//close
	
}
