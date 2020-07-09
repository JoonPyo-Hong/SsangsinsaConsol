package member;

import java.util.Scanner;

public class MemberMain {
	
	public void memberMain(MemberUser user) {
	
	Scanner scan = new Scanner(System.in);
	
	while(true) {
		
	System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
	System.out.println("1.마이페이지");
	System.out.println("2.상품구매");
	System.out.println();
	System.out.println("0.뒤로가기");
	System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
	System.out.print("번호를 입력하세요 : ");
	String num = scan.nextLine();
	
	if (num.equals("1")) {	//1번 선택시 마이페이지로 이동
		MemberMypage mypage = new MemberMypage();
		mypage.myPage(user);
		
		break;
	} else if (num.equals("2")) {	//2번 선택시 상품구매로 이동
		MemberProductBuy productBuy = new MemberProductBuy();
		productBuy.memberProductBuy(user);
		
		break;
	} else if (num.equals("0")) {
		//추후 메인 짜면 넣을예정
		
		break;
	}
	
	
	}
	
	}
}
