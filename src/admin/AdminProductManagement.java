package admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import common.DBUtil;

//상품관리
public class AdminProductManagement {
	public void management() {
		//스캐너선언
		Scanner scan = new Scanner(System.in);
		//while 통제용
		boolean roof = true;
		while(roof) {
		//출력문
		System.out.println("-------------------");
		System.out.println("1. 조회");
		System.out.println("2. 수정");
		System.out.println("3. 삭제");
		System.out.println("0. 뒤로가기");
		System.out.println("-------------------");
		System.out.print("선택(번호) : ");
		String input = scan.nextLine();
		if(input.equals("1")) {
			//1. 조회
			search();
		}else if(input.equals("2")) {
			
			//2. 수정
			fix();
		}else if(input.equals("3")) {
			//3. 삭제
			remove();
		}else if(input.equals("0")) {
			//0. 뒤로가기
			roof = false;
		}else {
			//유효성
			System.out.println("-------------------");
			System.out.println("잘못입력하셨습니다");
			System.out.println("계속하시려면 엔터를누르세요...");
			String enter = scan.nextLine();
		}
		
		
		
		
		}
	}
	//3. 삭제
	private void remove() {
		

				Scanner scan = new Scanner(System.in);
				System.out.print("삭제할 번호(숫자): ");
				String inputseq = scan.nextLine();
				try {
					int s = Integer.parseInt(inputseq);
					//객체 생성
					Connection conn = null;
					PreparedStatement stat = null;
				
					DBUtil util = new DBUtil();
					// 구현부
				      try {
				    	  	// 연결
					         conn = util.open();
				    	  
				    	

				         // select문 삽입
				         String sql = "update TBL_PRODUCT_DETAIL set delflag = 1 where TBL_PRODUCT_SEQ = ?"  ;
				         stat = conn.prepareStatement(sql);
				         stat.setInt(1, s);
				         stat.executeUpdate();
				        
				         System.out.println("삭제가 완료되었습니다.");
				         // 자원 닫기
				         stat.close();
				         conn.close();
				     
				         // 예외 처리
				      	} catch (Exception e) {
				         System.out.println(e);
				  
				      	}
				} catch (Exception e) {
					System.out.println("-------------------");
					System.out.println("잘못입력하셨습니다");
					System.out.println("계속하시려면 엔터를누르세요...");
					String enter = scan.nextLine();
				}
				
				
		
	}
	// 2. 수정
	private void fix() {
		Scanner scan = new Scanner(System.in);
		System.out.print("수정할 번호(숫자): ");
		String input = scan.nextLine();
	
		try {
			int s = Integer.parseInt(input);
			System.out.print("브랜드: ");
			String q1 = scan.nextLine();
			System.out.print("제품명: ");
			String q2 = scan.nextLine();
			System.out.print("가격: ");
			String q3 = scan.nextLine();
			System.out.print("색상: ");
			String q4 = scan.nextLine();
			System.out.print("사이즈: ");
			String q5 = scan.nextLine();
			System.out.println("수정이 완료되었습니다.");
		} catch (Exception e) {
			System.out.println("-------------------");
			System.out.println("잘못입력하셨습니다");
			System.out.println("계속하시려면 엔터를누르세요...");
			String enter = scan.nextLine();
		}
	
		
		
	}

	// 1. 조회
	private void search() {
			System.out.println("[번호]\t[브랜드]\t[제품명]\t[가격]\t[등록일자]\t[사이즈]\t[보유수량]");
				//객체 생성
				Connection conn = null;
				Statement stat = null;
				ResultSet rs = null;
				DBUtil util = new DBUtil();
				int count =0;
				// 구현부
			      try {
			    	  	// 연결
				         conn = util.open();
			    	  
			    	
			         stat = conn.createStatement();

			         // select문 삽입
			         String sql = "SELECT d.TBL_PRODUCT_SEQ,c.name,p.name,p.price,p.regdate,d.product_size,d.quantity\r\n" + 
			         		"			         		FROM TBL_COMPANY  c\r\n" + 
			         		"			         		inner join TBL_PRODUCT p on c.seq=p.seq\r\n" + 
			         		"			         		 inner join TBL_PRODUCT_DETAIL d on p.seq=d.tbl_product_seq order by d.TBL_PRODUCT_SEQ";
			         rs = stat.executeQuery(sql);
			         
			         // select문 마지막행까지 반복
			         while(rs.next()) {
			        	 count++;
			        	 System.out.print(rs.getString(1)+"\t"+rs.getString(2)+"\t"
			        			 +rs.getString(3)+"\t"+rs.getString(4)+"\t"
			        			 +rs.getString(5).replace("00:00:00", "")+"\t"+rs.getString(6)+"\t"+rs.getString(7));
			        	 System.out.println();
			        	 if(count==20) {
			        		 count=0;
			        		 break;
			        	 }
			         }
			         	
			         // 자원 닫기
			         stat.close();
			         conn.close();
			     
			         // 예외 처리
			      	} catch (Exception e) {
			         System.out.println(e);
			  
			      	}
	}
}
