package admin;

import java.util.Scanner;

public class AdminMain {
	
	public void adminMain(AdminUser user) {
		while(true) {
			
			Scanner scan = new Scanner(System.in);
			System.out.println("===== M A I N=====");
			System.out.println("1. 상품 관리");
			System.out.println("2. 회원 관리");
			System.out.println("3. 공지 관리");
			System.out.println("4. 판매내역 관리");
			System.out.println("0. 로그 아웃");
			System.out.println("==================");
			System.out.print("번호 : ");
			String index = scan.nextLine();
			if (index.equals("1")) {
				AdminProductManagement adminProductManagement = new AdminProductManagement();
				adminProductManagement.management();
			} else if (index.equals("2")) {
				AdminMemberManagement adminMemberManagement = new AdminMemberManagement();
				adminMemberManagement.memberMngMain(user);
			} else if (index.equals("3")) {
				AdminNoticeManagement adminNoticeManagement = new AdminNoticeManagement();
				adminNoticeManagement.noticeMngMain(user);
			} else if (index.equals("4")) {
				AdminSaledlistManagement adminSaledlistManagement = new AdminSaledlistManagement();
				adminSaledlistManagement.memberSaledlistMain(user);
			} else if (index.equals("0")) {
				break;
			}
		
		} 
	}

}
