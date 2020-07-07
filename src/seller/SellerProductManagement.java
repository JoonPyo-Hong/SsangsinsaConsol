package seller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import common.DBUtil;

public class SellerProductManagement { // 1. 상품관리
	
	Connection conn = null;
	PreparedStatement pstat = null;
	ResultSet rs = null;
	DBUtil util = new DBUtil();
	Scanner scan = new Scanner(System.in);
	Map<String, String> map = new HashMap<String, String>();

	public void sellerProduct(SellerUser seller) {
		
		check(seller); // 상품 전체 출력
		
		while(true) {
			
			System.out.println("상품 관리 화면");
			System.out.println("=============");
			System.out.println("1. 상품 등록");
			System.out.println("2. 상품 정보 수정");
			System.out.println("3. 상품 삭제");
			System.out.println("4. 교환/반품");
			System.out.println("0. 뒤로가기");
			System.out.println("=============");
			System.out.print("번호 : ");
			String index = scan.nextLine();
			System.out.println("=============\n");
			
			if (index.equals("1")) {
				// 상품 등록 메소드 실행
				insert(seller);
				
			} else if (index.equals("2")) {
				// 상품 정보 수정 메소드 실행
				update(seller);
				
			} else if (index.equals("3")) {
				// 상품 삭제 메소드 실행
				delete(seller);
				
			} else if (index.equals("4")) {
				// 교환/반품 메소드 실행
				afterService(seller);
				
			} else if (index.equals("0")) {
				// 뒤로가기
				System.out.println("이전 페이지로 돌아갑니다.\n");
				break;
				
			} else {
				System.out.println("잘못된 번호입니다.\n다시 입력해 주세요.\n");
				continue;
			}
			
		}
		
	}//menu

	
	
	
	
	
	private void afterService(SellerUser seller) { // 교환/반품
		
		while (true) {
		
			try {
				
				conn = util.open();
				
				System.out.println("교환 / 반품 목록입니다.\n");
				
				String sql = "SELECT SUP.SEQ, P.NAME, SL.QUANTITY, S.SALEDDATE, SUP.REGDATE, SUP.TYPE, SUP.REASON, S.SEQ AS SALESEQ "
						+ "FROM TBL_SUPPORT SUP INNER JOIN TBL_SALE S ON SUP.TBL_SALE_SEQ = S.SEQ INNER JOIN TBL_SALELIST SL "
						+ "ON S.SEQ = SL.TBL_SALE_SEQ INNER JOIN TBL_PRODUCT P ON SL.TBL_PRODUCT_SEQ = P.SEQ "
						+ "WHERE P.TBL_COMPANY_SEQ = ? AND SUP.DELFLAG = 0 ORDER BY SUP.REGDATE";
				
				pstat = conn.prepareStatement(sql);
				
				pstat.setString(1, seller.getSeq());
				
				rs = pstat.executeQuery();
				
				while (rs.next()) {
					
					System.out.printf("번호 : %s\n", rs.getString("seq"));
					System.out.printf("상품명 : %s\n", rs.getString("name"));
					System.out.printf("수량 : %s\n", rs.getString("quantity"));
					System.out.printf("판매날짜 : %s\n", rs.getString("saleddate").substring(0, 10).trim());
					System.out.printf("신청날짜 : %s\n", rs.getString("regdate").substring(0, 10).trim());
					System.out.printf("타입 : %s\n", rs.getString("type"));
					System.out.printf("사유 : %s\n\n", rs.getString("reason"));
					
					map.put(rs.getString("seq"), rs.getString("saleseq"));
					
				}
				
				rs.close();
				pstat.close();
				
				System.out.println("교환 / 반품 처리할 상품의 번호를 입력하세요.");
				System.out.print("번호 : ");
				String num = scan.nextLine();
				
				sql = "UPDATE TBL_SUPPORT SET DELFLAG = 1 WHERE SEQ = ? AND TBL_SALE_SEQ = ?";
				
				pstat = conn.prepareStatement(sql);
				
				pstat.setString(1, num);
				pstat.setString(2, map.get(num));
				
//				pstat.executeQuery();
				
				System.out.printf("\n%s번 상품이 교환 / 반품 처리되었습니다.\n\n", num);
				
				pstat.close();
				conn.close();
				break;
				
			} catch (Exception e) {
//				e.printStackTrace();
				System.out.println("Error!!\n정확한 정보를 입력해 주세요.\n");
				
			}
		
		}
		
	}//afterService






	private void delete(SellerUser seller) { // 상품 정보 삭제
		
		while (true) {
		
			try {
				
				conn = util.open();
				
				System.out.println("삭제할 상품의 번호를 입력해 주세요.");
				
				String sql = "UPDATE TBL_PRODUCT SET DELFLAG = 0 "
						+ "WHERE SEQ = ? AND TBL_COMPANY_SEQ = ?";
				
				System.out.print("번호 : ");
				String num = scan.nextLine();
				
				pstat = conn.prepareStatement(sql);
				
				pstat.setString(1, num);
				pstat.setString(2, seller.getSeq());
				
//				pstat.executeQuery();
				
				System.out.printf("\n%s번 상품이 삭제되었습니다.\n\n", num);
				
				pstat.close();			
				conn.close();
				break;
				
			} catch (Exception e) {
//				e.printStackTrace();
				System.out.println("Error!!\n정확한 정보를 입력해 주세요.\n");
			}
		
		}
		
	}//delete






	private void update(SellerUser seller) { // 상품 정보 수정
		
		while (true) {
		
			try {
				
				conn = util.open();
				
				System.out.println("수정할 상품의 번호를 입력해 주세요.");
				
				String sql = "UPDATE TBL_PRODUCT SET NAME = ?, PRICE = ? WHERE SEQ = ? AND TBL_COMPANY_SEQ = ? AND DELFLAG = 1";
				
				System.out.print("번호 : ");
				String num = scan.nextLine();
				
				System.out.println("\n수정 할 정보를 입력해 주세요.");
				
				System.out.print("상품명 : ");
				String productName = scan.nextLine();
				
				System.out.print("가격 : ");
				String productPrice = scan.nextLine();
				
				pstat = conn.prepareStatement(sql);
				
				pstat.setString(1, productName);
				pstat.setString(2, productPrice);
				pstat.setString(3, num);
				pstat.setString(4, seller.getSeq());
				
//				pstat.executeQuery();
				
				System.out.printf("\n%s번 상품이 수정되었습니다.\n\n", num);
				
				pstat.close();			
				conn.close();
				break;
				
			} catch (Exception e) {
//				e.printStackTrace();
				System.out.println("Error!!\n정확한 정보를 입력해 주세요.\n");
			}
		
		}
		
	}//update


	
	
	
	
	private void insert(SellerUser seller) { // 상품 등록
		
		while (true) {
			
			System.out.println("상품 등록 화면");
			System.out.println("=============");
			System.out.println("1. 신규 등록");
			System.out.println("2. 상세 정보 등록");
			System.out.println("0. 뒤로가기");
			System.out.println("=============");
			System.out.print("번호 : ");
			String index = scan.nextLine();
			System.out.println("=============\n");
			
			if (index.equals("1")) { // 신규 등록
				
				while (true) {
				
					try {
						
						conn = util.open();
						
						System.out.println("등록할 상품의 정보를 입력해 주세요.");
						
						String sql = "INSERT INTO TBL_PRODUCT (SEQ, NAME, PRICE, TBL_COMPANY_SEQ, REGDATE, IMAGE_PATH, DELFLAG) "
								+ "VALUES (TBL__PRODUCT_SEQ.NEXTVAL, ?, ?, ?, SYSDATE, NULL, 1)";
						
						System.out.print("상품명 : ");
						String productName = scan.nextLine();
						
						System.out.print("가격 : ");
						String productPrice = scan.nextLine();
						
						pstat = conn.prepareStatement(sql);
						
						pstat.setString(1, productName);
						pstat.setString(2, productPrice);
						pstat.setString(3, seller.getSeq());
						
//						pstat.executeQuery();
						
						System.out.println("\n상품이 등록되었습니다.\n");
						
						pstat.close();
						conn.close();
						break;
						
					} catch (Exception e) {
//						e.printStackTrace();
						System.out.println("Error!!\n정확한 정보를 입력해 주세요.\n");
					}
					
				}
				
			} else if (index.equals("2")) { // 상세 정보 등록
				
				while (true) {
				
					try {
						
						conn = util.open();
						
						System.out.println("등록할 상품의 번호를 입력해 주세요.");
						
						String sql = "INSERT INTO TBL_PRODUCT_DETAIL (TBL_PRODUCT_SEQ, PRODUCT_SIZE, QUANTITY, DELFLAG) "
								+ "VALUES (?, ?, ?, 1)";
						
						System.out.print("번호 : ");
						String num = scan.nextLine();
						
						System.out.println("\n등록 할 정보를 입력해 주세요.");
						
						System.out.print("사이즈(S,M,L) : ");
						String size = scan.nextLine();
						
						System.out.print("수량(숫자만 입력해 주세요) : ");
						String quantity = scan.nextLine();
						
						pstat = conn.prepareStatement(sql);
						
						pstat.setString(1, num);
						pstat.setString(2, size.toUpperCase());
						pstat.setString(3, quantity);
						
//						pstat.executeQuery();
						
						System.out.println("\n상품이 등록되었습니다.\n");
						
						pstat.close();
						conn.close();
						break;
						
					} catch (Exception e) {
//						e.printStackTrace();
						System.out.println("Error!!\n정확한 정보를 입력해 주세요.\n");
					}
					
				}
				
			} else if (index.equals("0")) {
				// 뒤로가기
				System.out.println("이전 페이지로 돌아갑니다.\n");
				break;
				
			} else {
				System.out.println("잘못된 번호입니다.\n다시 입력해 주세요.\n");
				continue;
			}
			
		}
		
	}//insert
	
	
	
	
	
	
	private void check(SellerUser seller) { // 상품 전체 출력
		
		while(true) {
		
			try {
				
				conn = util.open();
	
				//로그인 한 판매자 번호를 포린키로 가지는 모든 상품 출력
				String sql = "SELECT P.SEQ, P.NAME, P.PRICE, P.REGDATE, P.IMAGE_PATH, PD.PRODUCT_SIZE, PD.QUANTITY "
						+ "FROM TBL_PRODUCT P INNER JOIN TBL_PRODUCT_DETAIL PD ON P.SEQ=PD.TBL_PRODUCT_SEQ WHERE P.TBL_COMPANY_SEQ = ?";
				
				pstat = conn.prepareStatement(sql);
				
				pstat.setString(1, seller.getSeq());
				
				rs = pstat.executeQuery();
				
				System.out.println("등록한 모든 상품을 조회합니다.\n");
				
				while (rs.next()) {
					
					System.out.printf("번호 : %s\n", rs.getString("seq"));
					System.out.printf("상품명 : %s\n", rs.getString("name"));
					System.out.printf("가격 : %s\n", rs.getString("price"));
					System.out.printf("등록일 : %s\n", rs.getString("regdate").substring(0, 10).trim());
					System.out.printf("사이즈 : %s\n", rs.getString("product_size"));
					System.out.printf("수량 : %s\n\n", rs.getString("quantity"));
					
				}
				
				rs.close();
				pstat.close();
				conn.close();
				break;
				
			} catch (Exception e) {
//				e.printStackTrace();
				System.out.println("Error!!\n");
				break;
			}
		
		}
		
	}//check

	
}//class
