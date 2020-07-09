package member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import common.DBUtil;

public class MemberMypage {
	
	Connection conn = null;
    Statement stat = null;
    PreparedStatement pstat = null;
    DBUtil util = new DBUtil();
    ResultSet rs = null;
    Scanner scan = new Scanner(System.in);

	
	
	
	public void myPage(MemberUser user) {
		
		
		
	    
	    
	    try {
	    	
	    	conn =util.open();
			
			
			//로그인한 회원 정보 select
			String sql = "SELECT M.NAME AS MEMBERNAME,P.NAME AS PRODUCTNAME,P.PRICE,S.SALEDDATE,SL.QUANTITY FROM TBL_MEMBER M\r\n" + 
					"    INNER JOIN TBL_SALE S\r\n" + 
					"        ON M.SEQ = S.TBL_MEMBER_SEQ\r\n" + 
					"            INNER JOIN TBL_SALELIST SL\r\n" + 
					"                ON S.SEQ = SL.TBL_SALE_SEQ\r\n" + 
					"                    INNER JOIN TBL_PRODUCT P\r\n" + 
					"                        ON P.SEQ = SL.TBL_PRODUCT_SEQ WHERE M.SEQ = ?";
			
			pstat = conn.prepareStatement(sql);
			
			//로그인한 회원의 회원번호를 select 구문에 넣어준다.
			pstat.setInt(1, user.getSeq());
			
			pstat.executeUpdate();
			
			rs = pstat.executeQuery(sql);
			
			//회원정보 출력
			System.out.println("〓〓〓〓〓〓〓〓〓MY PAGE〓〓〓〓〓〓〓〓〓");
			System.out.println("〓〓〓〓〓〓〓〓〓"+user.getName()+"님 기본정보〓〓〓〓〓〓〓〓〓〓");
			System.out.println("ID : " + user.getId());
			System.out.println("이름 : " + user.getName());
			System.out.println("주소 : " + user.getAddress());
			System.out.println("가입일 : " + user.getRegdate());
			System.out.println();
			System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓"+user.getName()+"님 구매정보〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("[상품이름]\t\t\t\t\t\t[가격]\t[수량]\t[구매날짜]");
			
			//회원의 구매내역 출력
			while (rs.next()) {

				
				System.out.print(rs.getString("PRODUCTNAME")+"\t\t\t");
				System.out.print(rs.getString("PRICE")+"\t");
				System.out.print(rs.getString("QUANTITY")+"\t");
				System.out.println(rs.getString("SALEDDATE"));
				

			}
			
			
			pstat.close();
			conn.close();
			
			//뒤로가기 구현
			System.out.println();
			System.out.print("뒤로가려면 0을 입력하세요 :");
			String backNum = scan.nextLine();
			
			if(backNum.equals("0")) {
				MemberMain memberMain = new MemberMain();
				memberMain.memberMain(user);
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

}