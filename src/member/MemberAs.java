package member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import common.DBUtil;

public class MemberAs {
	private Connection conn = null;
	private PreparedStatement pstat = null;
	private ResultSet rs = null;
	private DBUtil util = new DBUtil();
	private Scanner scan = new Scanner(System.in);

	public void memberAsMain(MemberUser memberUser) {
		while (true) {

			List<MemberSaleList> saleList = showSaleList(memberUser);
			System.out.print("교환/배송을 할 상품 번호를 입력해주세요 : ");
			String productNum = scan.nextLine();
			if(checkInput(saleList, productNum)) {
				System.out.println("=================");
				System.out.println("1. 교환하기");
				System.out.println("2. 반품하기");
				System.out.println("0. 뒤로가기");
				System.out.println("=================");
				System.out.print("입력 : ");
				String input = scan.nextLine();
				if (input.equals("1")) {
					change(productNum);
				} else if (input.equals("2")) {
					returnMoney(productNum);
				} else if(input.equals("0")){
					break;
				}
			} else {
				System.out.println("올바른 숫자를 입력해주세요!");
			}
			
			
		}
	}

	private void returnMoney(String productNum) {
		while (true) {
			System.out.println("1. 사이즈 불량\t2. 상품 불량\t3. 단순 변심\t4. 오배송\t0.뒤로기가");
			System.out.print("교환 사유를 입력해주세요! : ");
			String inputReason = scan.nextLine();
			String[] reason = { "사이즈 불량", "상품 불량", "단순 변심", "오배송" };
			if(inputReason.equals("0")) {
				break;
			}
			if (Integer.parseInt(inputReason) > reason.length) {
				System.out.println("올바른 번호를 입력해주세요!");
				System.out.println("다시 선택하시려면 엔터를 입력해주세요!");
				scan.nextLine();
			} else {
				insertAS(productNum, "반품", reason[Integer.parseInt(inputReason) -1]);
				break;
				
			}
		}
		
	}

	private boolean checkInput(List<MemberSaleList> saleList, String productNum) {
		String pattern = "^[0-9]+$";
		if(!productNum.matches(pattern)){
			return false;
		}
		for (int i = 0; i < saleList.size(); i++) {
			if(saleList.get(i).getSeq() == Integer.parseInt(productNum)) {
				return true;
			}
		}
		return false;
		
	}

	private void change(String productNum) {
		while (true) {
			System.out.println("1. 사이즈 불량\t2. 상품 불량\t3. 단순 변심\t4. 오배송\t0.뒤로기가");
			System.out.print("교환 사유를 입력해주세요! : ");
			String inputReason = scan.nextLine();
			String[] reason = { "사이즈 불량", "상품 불량", "단순 변심", "오배송" };
			if(inputReason.equals("0")) {
				break;
			}
			if (Integer.parseInt(inputReason) > reason.length) {
				System.out.println("올바른 번호를 입력해주세요!");
				System.out.println("다시 선택하시려면 엔터를 입력해주세요!");
				scan.nextLine();
			} else {
				insertAS(productNum, "교환", reason[Integer.parseInt(inputReason) -1]);
				break;
				
			}
		}
		
		
		
	}

	private void insertAS(String productNum, String type, String reason) {
		try {
			conn = util.open();
			String sql = "INSERT INTO TBL_SUPPORT(SEQ, REGDATE, TYPE, REASON, DELFLAG, TBL_SALE_SEQ) "
					+ "VALUES(SEQ_SUPPORT.NEXTVAL, SYSDATE, ? , ?, DEFAULT, ?)";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, type);
			pstat.setString(2, reason);
			pstat.setInt(3, Integer.parseInt(productNum));
			pstat.executeUpdate();
			pstat.close();
			conn.close();
			System.out.println("교환/반품 처리가 완료되었습니다!");
			System.out.println("계속하시려면 엔터를 입력해주세요!");
			scan.nextLine();
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	private List<MemberSaleList> showSaleList(MemberUser memberUser) {
		List<MemberSaleList> saleList = new ArrayList<MemberSaleList>();
		try {
			String sql = "SELECT \r\n" + 
					"    SALE.SEQ AS SEQ,\r\n" + 
					"    SALE.SALEDDATE AS SALEDDATE,\r\n" + 
					"    PROD.NAME AS PRODNAME,\r\n" + 
					"    SALLIST.QUANTITY AS QUANTITY,\r\n" + 
					"    COMP.NAME AS COMPANY\r\n" + 
					"FROM TBL_MEMBER MEM\r\n" + 
					"INNER JOIN TBL_SALE SALE\r\n" + 
					"ON MEM.SEQ = SALE.TBL_MEMBER_SEQ\r\n" + 
					"INNER JOIN TBL_SALELIST SALLIST\r\n" + 
					"ON SALE.SEQ = SALLIST.TBL_SALE_SEQ\r\n" + 
					"INNER JOIN TBL_PRODUCT PROD\r\n" + 
					"ON PROD.SEQ = SALLIST.TBL_PRODUCT_SEQ\r\n" + 
					"INNER JOIN TBL_COMPANY COMP\r\n" + 
					"ON COMP.SEQ = PROD.TBL_COMPANY_SEQ\r\n" + 
					"WHERE MEM.SEQ = ?\r\n" + 
					"AND MEM.DELFLAG = 0 AND SALE.DELFLAG = 0 AND SALLIST.DELFLAG = 0\r\n" + 
					"AND PROD.DELFLAG = 0 AND COMP.DELFLAG = 0\r\n"+
					"AND SYSDATE - SALE.SALEDDATE <= 10"+ 
					"ORDER BY SEQ ASC";
			conn = util.open();
			pstat = conn.prepareStatement(sql);
			pstat.setInt(1, memberUser.getSeq());
			rs = pstat.executeQuery();
			while(rs.next()) {
				MemberSaleList memberSaleList = new MemberSaleList(
						rs.getInt("SEQ"),
						rs.getString("SALEDDATE"),
						rs.getString("PRODNAME"),
						rs.getInt("QUANTITY"),
						rs.getString("COMPANY")
						);
				saleList.add(memberSaleList);
			}
			printList(saleList);
			return saleList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private void printList(List<MemberSaleList> saleList) {
		System.out.println("상품 목록입니다.");
		System.out.println("주문번호\t\t구입일\t\t\t제품명\t\t\t수량\t판매자");
		System.out.println("========================================================================================");
		for (int i = 0; i < saleList.size(); i++) {
			MemberSaleList memberSaleList = saleList.get(i);
			System.out.println(memberSaleList.getSeq() + "\t" + memberSaleList.getSaledate() + "\t" + String.format("%25s", memberSaleList.getProdname()) + "\t" + memberSaleList.getQuantity() + "\t" + memberSaleList.getCompany());
			
		}
		System.out.println("========================================================================================");
		
	}

		
}
