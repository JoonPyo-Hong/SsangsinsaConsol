package seller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import common.DBUtil;

/**
 * 
 * @author 김동욱
 * 배송 관련 클래스
 */
public class SellerRegisterDelivery {
	private Connection conn = null;
	private PreparedStatement pstat = null;
	private ResultSet rs = null;
	private DBUtil util = new DBUtil();
	private Scanner scan = new Scanner(System.in);

	/**
	 * 배송 - 메인화면
	 * @param 판매자 정보
	 */
	public void SellerDeliveryMain(SellerUser sellerUser) {
		while (true) {
			System.out.println("배송 등록 화면");
			System.out.println("=================");
			System.out.println("1. 배송 등록 하기");
			System.out.println("2. 배송 완료 처리하기");
			System.out.println("0. 뒤로가기");
			System.out.println("=================");
			System.out.print("입력 : ");
			String input = scan.nextLine();
			if (input.equals("1")) {
				// 배송 등록
				insertDelivery(sellerUser);
			} else if (input.equals("2")) {
				// 배송 완료
				finishDeliver(sellerUser);
			} else if (input.equals("0")) {
				break;
			}

		}

	}

	private void finishDeliver(SellerUser sellerUser) {
		List<DeliveryInfo> deliveringList = new ArrayList<DeliveryInfo>();	// 배송 정보를 담을 객체
		// 배송중인 상품의 정보를 출력
		System.out.println("배송 중 판매 목록입니다.");
		System.out.println("주문번호\t\t상품명\t\t\t수량\t\t판매(교환)일\t\t배송지");
		System.out.println(
				"==========================================================================================================");
		try {
			conn = util.open();
			String sql = "SELECT\r\n" + "DELST.SEQ AS SEQ, \r\n" + "PROD.NAME AS NAME, \r\n"
					+ "SALST.QUANTITY AS QUANTITY, \r\n" + "CASE\r\n"
					+ "    WHEN DELST.TBL_SALE_SEQ IS NULL THEN SPT.REGDATE\r\n"
					+ "    WHEN DELST.TBL_SALE_SEQ IS NOT NULL THEN SALE.SALEDDATE\r\n" + "END AS REGDATE,\r\n"
					+ "DELST.DESTINATION AS DESTINATION\r\n" + "FROM TBL_COMPANY COMP\r\n"
					+ "INNER JOIN TBL_PRODUCT PROD\r\n" + "ON COMP.SEQ = PROD.TBL_COMPANY_SEQ\r\n"
					+ "INNER JOIN TBL_SALELIST SALST\r\n" + "ON PROD.SEQ = SALST.TBL_PRODUCT_SEQ\r\n"
					+ "INNER JOIN TBL_SALE SALE\r\n" + "ON SALE.SEQ = SALST.TBL_SALE_SEQ\r\n"
					+ "LEFT OUTER JOIN TBL_SUPPORT SPT\r\n" + "ON SALE.SEQ = SPT.TBL_SALE_SEQ\r\n"
					+ "INNER JOIN TBL_DELIVERY_LIST DELST\r\n"
					+ "ON SALE.SEQ = DELST.TBL_SALE_SEQ OR SPT.SEQ = DELST.TBL_SUPPORT_SEQ\r\n"
					+ "WHERE COMP.SEQ = ? AND DELST.STATUS = '배송중' \r\n"
					+ "AND COMP.DELFLAG = 0 AND PROD.DELFLAG = 0 AND SALST.DELFLAG = 0 AND SALE.DELFLAG = 0\r\n"
					+ "AND NVL(SPT.DELFLAG, 0) = 0 AND DELST.DELFLAG = 0";
				
			pstat = conn.prepareStatement(sql);
			pstat.setInt(1, sellerUser.getSeq());
			rs = pstat.executeQuery();
			
			while(rs.next()) {
				DeliveryInfo delivering = new DeliveryInfo(
						rs.getInt("SEQ"), 
						rs.getString("NAME"), 
						rs.getInt("QUANTITY"), 
						rs.getString("REGDATE"), 
						rs.getString("DESTINATION"));
				deliveringList.add(delivering);	// 쿼리문 실행 결과를 arrayList에 저장을 한다.
			}
			printList(deliveringList);	// 상품 정보 출력
			pstat.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(
				"==========================================================================================================");
		System.out.print("배송 완료를 할 주문의 번호를 입력해주세요. : ");
		String input = scan.nextLine();
		// 유효성 검사 및 배송 완료 처리
		if(checkInput(input, deliveringList)) {
			registFinish(input);
		} else {
			System.out.println("올바른 번호를 입력해주세요!");
		}
	}

	// 배송 완료 처리
	private void registFinish(String input) {
		try {
			conn = util.open();
			
			String sql = "UPDATE TBL_DELIVERY_LIST SET STATUS = ? WHERE SEQ = ?";
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, "배송완료");
			pstat.setInt(2, Integer.parseInt(input));
			
			pstat.executeUpdate();
			
			pstat.close();
			conn.close();
			System.out.println("배송이 완료되었습니다! 계속하시려면 엔터를 입력해주세요!");
			scan.nextLine();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 배송 등록
	private void insertDelivery(SellerUser sellerUser) {
		List<DeliveryInfo> notDeliveredList = new ArrayList<DeliveryInfo>();	// 배송 전 판매 목록을 담을 arrayList
		System.out.println("배송 전 판매 목록입니다.");
		System.out.println("주문번호\t\t상품명\t\t\t수량\t\t판매(교환)일\t\t배송지");
		System.out.println(
				"==========================================================================================================");
		try {
			conn = util.open();
			String sql = "SELECT\r\n" + "DELST.SEQ AS SEQ, \r\n" + "PROD.NAME AS NAME, \r\n"
					+ "SALST.QUANTITY AS QUANTITY, \r\n" + "CASE\r\n"
					+ "    WHEN DELST.TBL_SALE_SEQ IS NULL THEN SPT.REGDATE\r\n"
					+ "    WHEN DELST.TBL_SALE_SEQ IS NOT NULL THEN SALE.SALEDDATE\r\n" + "END AS REGDATE,\r\n"
					+ "DELST.DESTINATION AS DESTINATION\r\n" + "FROM TBL_COMPANY COMP\r\n"
					+ "INNER JOIN TBL_PRODUCT PROD\r\n" + "ON COMP.SEQ = PROD.TBL_COMPANY_SEQ\r\n"
					+ "INNER JOIN TBL_SALELIST SALST\r\n" + "ON PROD.SEQ = SALST.TBL_PRODUCT_SEQ\r\n"
					+ "INNER JOIN TBL_SALE SALE\r\n" + "ON SALE.SEQ = SALST.TBL_SALE_SEQ\r\n"
					+ "LEFT OUTER JOIN TBL_SUPPORT SPT\r\n" + "ON SALE.SEQ = SPT.TBL_SALE_SEQ\r\n"
					+ "INNER JOIN TBL_DELIVERY_LIST DELST\r\n"
					+ "ON SALE.SEQ = DELST.TBL_SALE_SEQ OR SPT.SEQ = DELST.TBL_SUPPORT_SEQ\r\n"
					+ "WHERE COMP.SEQ = ? AND DELST.STATUS IS NULL \r\n"
					+ "AND COMP.DELFLAG = 0 AND PROD.DELFLAG = 0 AND SALST.DELFLAG = 0 AND SALE.DELFLAG = 0\r\n"
					+ "AND NVL(SPT.DELFLAG, 0) = 0 AND DELST.DELFLAG = 0";

			pstat = conn.prepareStatement(sql);
			pstat.setInt(1, sellerUser.getSeq());
			rs = pstat.executeQuery();
			while (rs.next()) {
				DeliveryInfo notDelivered = new DeliveryInfo(
						rs.getInt("SEQ"), 
						rs.getString("NAME"), 
						rs.getInt("QUANTITY"), 
						rs.getString("REGDATE"), 
						rs.getString("DESTINATION"));
				notDeliveredList.add(notDelivered);
			}
			// 배송 전 목록 출력
			printList(notDeliveredList);
			pstat.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println(
				"==========================================================================================================");
		System.out.print("배송등록을 할 주문의 번호를 입력해주세요. : ");
		String input = scan.nextLine();
		// 유효성 검사 및 배송중 등록
		if(checkInput(input, notDeliveredList)) {
			registWorking(input);
		} else {
			System.out.println("올바른 번호를 입력해주세요!");
		}

	}

	// 배송중 등록
	private void registWorking(String input) {
		try {
			conn = util.open();
			
			String sql = "UPDATE TBL_DELIVERY_LIST SET STATUS = ? WHERE SEQ = ?";
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, "배송중");
			pstat.setInt(2, Integer.parseInt(input));
			
			pstat.executeUpdate();
			
			pstat.close();
			conn.close();
			System.out.println("배송등록이 완료되었습니다! 계속하시려면 엔터를 입력해주세요!");
			scan.nextLine();
		}catch (Exception e) {
			e.printStackTrace();
		}
		

	}

	// 입력 숫자 및 목록에 있는지 유효성 검사
	private boolean checkInput(String input, List<DeliveryInfo> notDeliveredList) {
		String pattern = "^[0-9]+$";
		if(!input.matches(pattern)) {
			return false;
		}
		for (int i = 0; i < notDeliveredList.size(); i++) {
			if(notDeliveredList.get(i).getSeq() == Integer.parseInt(input)) {
				return true;
			}
		}
		return false;
	}

	//입력받은 arrayList를 출력해주는 메소드
	private void printList(List<DeliveryInfo> notDeliveredList) {
		for (int i = 0; i < notDeliveredList.size(); i++) {
			DeliveryInfo notDelivered = notDeliveredList.get(i);
			System.out.println(
					notDelivered.getSeq() + "\t" + notDelivered.getProductName() + "\t" + notDelivered.getQuantity()
							+ "\t" + notDelivered.getRegdate() + "\t" + notDelivered.getDestination());
		}
	}
}
