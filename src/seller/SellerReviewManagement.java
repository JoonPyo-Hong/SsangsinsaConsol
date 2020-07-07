package seller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import common.DBUtil;

public class SellerReviewManagement { // 2. 리뷰관리
	
	Connection conn = null;
	PreparedStatement pstat = null;
	ResultSet rs = null;
	DBUtil util = new DBUtil();
	Scanner scan = new Scanner(System.in);
	
	public void sellReview(SellerUser seller) {
		
		while(true) {
			
			System.out.println("상품 관리 화면");
			System.out.println("=============");
			System.out.println("1. 답글 등록");
			System.out.println("2. 답글 수정");
			System.out.println("3. 답글 삭제");
			System.out.println("0. 뒤로가기");
			System.out.println("=============");
			System.out.print("번호 : ");
			String index = scan.nextLine();
			System.out.println("=============\n");
			
			if (index.equals("1")) {
				// 답글 등록하기
//				insert(seller);
				continue;
				
			} else if (index.equals("2")) {
				// 답글 수정하기
//				update(seller);
				continue;
				
			} else if (index.equals("3")) {
				// 답글 삭제하기
//				delete(seller);
				continue;
				
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

}//class
