package member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import common.DBUtil;

public class MemberProductBuy {
	
	Connection conn = null;
    Statement stat = null;
    PreparedStatement pstat = null;
    DBUtil util = new DBUtil();
    ResultSet rs = null;
    Scanner scan = new Scanner(System.in);
	
	public void memberProductBuy(MemberUser user) {
		
		try {
			conn = util.open();
			stat = conn.createStatement();
			
			System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓상품 목록〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("[상품번호]\t[상품명]\t\t\t[가격]\t[상품 등록일]");
			
			//상품 목록 100개 가져오기
			String sql = "select * from (select * from TBL_PRODUCT) where rownum <= 100 order by seq";
			
			rs = stat.executeQuery(sql);
			
			//상품 목록 출력
			while (rs.next()) {
				System.out.print(rs.getInt("seq")+"\t");
				System.out.print(rs.getString("name")+"\t");
				System.out.print(rs.getString("price")+"\t");
				System.out.println(rs.getString("regdate").substring(0, 10)+"\t");
			
			}
			
			System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.print("구매할 상품번호를 입력하세요 : ");
			String productNum = scan.nextLine();
			System.out.print("구매할 수량 : ");
			String productCount = scan.nextLine();
			System.out.print("배송지 : ");
			String shippingAddress = scan.nextLine();
			
			
			
			rs.close();
			stat.close();
			
			int saleSeq = 0;
			
			stat = conn.createStatement();
			//판매테이블 구매한 데이터 추가
			sql = "INSERT INTO TBL_SALE(SEQ, TBL_MEMBER_SEQ, SALEDDATE, DELFLAG) VALUES(SALESEQ.NEXTVAL,"+ user.getSeq()  +",SYSDATE, 0)";
			stat.executeUpdate(sql);
			
			//판매테이블에 새롭게 추가한 시퀀스 가져오기
			String sql1 = "select * from (select * from tbl_Sale order by seq desc) where rownum = 1";
			rs = stat.executeQuery(sql1);
			while (rs.next() ) {
				saleSeq += rs.getInt("seq");
			}
			stat.close();
			
			stat = conn.createStatement();
			//상품판매내역테이블 구매한 데이터 추가
			sql = "INSERT INTO TBL_SALELIST(TBL_SALE_SEQ, TBL_PRODUCT_SEQ, QUANTITY, DELFLAG) VALUES("+saleSeq+","+ productNum + "," + productCount + ",0)";
			stat.executeUpdate(sql);
			
			
			stat.close();
			
			stat = conn.createStatement();
			//배송내역테이블 구매한 데이터 추가
			sql = "INSERT INTO TBL_DELIVERY_LIST(SEQ, TBL_SALE_SEQ, TBL_SUPPORT_SEQ, COMPELETEDATE, DESTINATION, STATUS, DELFLAG) VALUES(SEQ_DELIVERY.NEXTVAL,"+ saleSeq + ","+  null + "," + null + ",'" + shippingAddress + "',"+ null +",0)";
			stat.executeUpdate(sql);
			stat.close();
			
			stat = conn.createStatement();
			//구매한 상품 정보 가져오기
			sql1 = "SELECT * FROM TBL_PRODUCT WHERE SEQ =" + productNum;
			rs = stat.executeQuery(sql1);
			
			//입력한 시퀀스를 통해 가져온 정보를 저장할 곳
			String prodectName = "";
			int prodectPrice = 0;
			while (rs.next()) {
				prodectName += rs.getString("name");
				prodectPrice += rs.getInt("price");
			}
			
			
			
			System.out.println("〓〓〓〓〓〓〓〓구매 완료(구매 정보)〓〓〓〓〓〓〓〓");
			System.out.println("고객명 : "+user.getName());
			System.out.println("상품명 : " +prodectName);
			System.out.println("수량 : " +productCount);
			System.out.println("배송지 : "+ shippingAddress);
			System.out.println("총 금액 : "+prodectPrice * Integer.parseInt(productCount)+"원");
			
			
			conn.close();
			
			//뒤로가기
			System.out.println();
			System.out.print("뒤로가려면 0을 입력하세요 : ");
			String backNum = scan.nextLine();
			
			if(backNum.equals("0")) {
				MemberMain memberMain = new MemberMain();
				memberMain.memberMain(user);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("구매실패");
		}
		
		
	}

}
