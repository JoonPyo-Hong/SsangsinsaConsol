package seller;

import java.util.Scanner;

public class SellerMain {
	
	public void sellerMain(SellerUser seller) {
		
		Scanner scan = new Scanner(System.in);
		
		while (true) {
			
			System.out.println("판매자 로그인 화면");
			System.out.println("=============");
			System.out.println("1. 상품 관리");
			System.out.println("2. 리뷰 관리 (공사중)");
			System.out.println("3. 배송 등록");
			System.out.println("0. 로그 아웃");
			System.out.println("=============");
			System.out.print("번호 : ");
			String index = scan.nextLine();
			System.out.println("=============\n");
			
			if (index.equals("1")) {
				// 상품 관리 페이지로 이동
				SellerProductManagement product = new SellerProductManagement();
				product.sellerProduct(seller);
				
			} else if (index.equals("2")) {
				// 리뷰 관리 페이지로 이동
//				SellerReviewManagement review = new SellerReviewManagement();
//				review.sellReview(seller);
				System.out.println("추후에 추가될 예정입니당.\n");
				continue;
				
			} else if (index.equals("3")) {
				// 배송 등록 페이지로 이동
//				System.out.println("내꺼 아님;\n");
				SellerRegisterDelivery sellerRegisterDelivery = new SellerRegisterDelivery();
				sellerRegisterDelivery.SellerDeliveryMain(seller);
				
			} else if (index.equals("0")) {
				// 로그아웃
				System.out.printf("%s님 이용해 주셔서 감사합니다.\n", seller.getCompanyName());
				break;
				
			} else {
				System.out.println("잘못된 번호입니다.\n다시 입력해 주세요.\n");
				continue;
			}
			
		}
		
		
	}

}
