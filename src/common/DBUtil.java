package common;

import java.sql.Connection;
import java.sql.DriverManager;


//public 키워드에 자주 다는 주석
//JAVA DOC 주석

/**
 * 
 * @author 홍길동
 * 오라클 연결 클래스입니다.
 *
 */
public class DBUtil {
	public static Connection open() {

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

	   }

}



//프로젝트창 > 우클릭 > Export > javadoc > C:\Program Files\Java\jdk1.8.0_241\bin\javadoc.exe + configure
// > next >VM option > -locale ko_KR -encoding UTF-8 -charset UTF-8 -docencoding UTF-8 > finish
// >D:\class\jdbc\JDBCTest\doc > index.html (>설명문) (나중에 doc파일 산출물로 제출)