package seller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import common.DBUtil;

//판매자 로그인
public class SellerLogin {

	// 로그인 -> 엘엠씨 / utacy364

	public void login() {

		//객체 생성
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		DBUtil util = new DBUtil();
		Scanner scan = new Scanner(System.in);
		SellerUser user = new SellerUser();
		//입력받기
		System.out.print("아이디 : ");
		String id = scan.nextLine();
		System.out.print("비밀번호 : ");
		String pw = scan.nextLine();
		// 초기화 및 선언
		int control = 0;

		
		// 구현부
		try {
			
			//연결
			conn = util.open();
			
			stat = conn.createStatement();

			// select문 삽입
			String sql = "SELECT * FROM TBL_COMPANY";
			rs = stat.executeQuery(sql);

			// select문 마지막행까지 반복
			while(rs.next()) {

				if(id.equals(rs.getString(2)) && pw.equals(rs.getString(3))) {
					control++;

					user.setSeq(rs.getInt(1));
					user.setCompanyName(rs.getString(2));
					user.setPwd(rs.getString(3));
					user.setAddress(rs.getString(4));
					user.setOwner(rs.getString(5));
					user.setTel(rs.getString(6));
					user.setCompanyNum(rs.getLong(7));

				}
			}

			// 자원 닫기
			stat.close();
			conn.close();

			if (control == 0) {
	        	//로그인 실패 시
	        	System.out.println("일치하는 아이디/비밀번호가 없습니다.");	
	        	
	        } else if(control > 0) {
	        	//로그인 성공후 화면으로 이동
	        	System.out.printf("\n%s님 환영합니다!\n\n", user.getCompanyName());	 
	        	SellerMain main = new SellerMain();
	    		main.sellerMain(user);
	    		
	        }

		} catch (Exception e) {// 예외 처리
			e.printStackTrace();
		}
		
		
	}//login
	
	
}//class
