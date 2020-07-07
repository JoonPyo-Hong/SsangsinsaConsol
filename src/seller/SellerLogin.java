package seller;

import java.util.Scanner;

public class SellerLogin { //임시 로그인
	
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("로그인 화면");
		System.out.print("ID : ");
		String id = scan.nextLine();
		System.out.print("PWD : ");
		String pwd = scan.nextLine();
		
		SellerUser seller = new SellerUser();
		
		seller.setSeq(1);
		seller.setCompanyName("엘엠씨");
		seller.setPwd("utacy364");
		seller.setAddress("서울특별시 강남구 압구정로42길 26 (신사동, 현우빌딩)");
		seller.setOwner("주식회사 레이어");
		seller.setTel("02-511-7288");
		seller.setCompanyNum(2618101530L);
		
		System.out.printf("\n%s님 환영합니다!\n\n", seller.getCompanyName());
		
		SellerMain main = new SellerMain();
		main.sellerMain(seller);
		
	}

}
