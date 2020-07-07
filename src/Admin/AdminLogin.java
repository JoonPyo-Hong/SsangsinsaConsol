package Admin;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

//관리자 로그인
public class AdminLogin {
	public void login() {
		
		
		//객체 생성
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		DBUtil util = new DBUtil();
		Scanner scan = new Scanner(System.in);
		AdminUser user = new AdminUser();
		//입력받기
		System.out.print("아이디 : ");
		String id = scan.nextLine();
		System.out.print("비밀번호 : ");
		String pw = scan.nextLine();
		// 초기화 및 선언
		int control = 0;
		
		// 구현부
	      try {
	    	  	// 연결
		         conn = util.open();
	    	  
	    	
	         stat = conn.createStatement();

	         // select문 삽입
	         String sql = "select * from TBL_ADMIN";
	         rs = stat.executeQuery(sql);
	         
	         // select문 마지막행까지 반복
	         while(rs.next()) {
	           if(id.equals(rs.getString(2))&&pw.equals(rs.getString(3))) {
	        	   control++;
	        	   int s = Integer.parseInt(rs.getString(1));
	        	   int d = Integer.parseInt(rs.getString(4));
	        	   user.setSeq(s);
	        	   user.setID(rs.getString(2));
	        	   user.setPWD(rs.getString(3));
	        	   user.setDELFLAG(d);
	           }
	         }
	         	
	         // 자원 닫기
	         stat.close();
	         conn.close();
	        if(control==0) {
	        	System.out.println("일치하는 아이디/비밀번호가 없습니다.");
	        }else if(control>0) {
	        	//로그인 성공후 화면으로 이동
	        	
	        }
	         // 예외 처리
	      	} catch (Exception e) {
	         System.out.println(e);
	  
	      	}
		
	}
}
