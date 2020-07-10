package main;

import java.util.Scanner;

import admin.AdminLogin;
import member.MemberJoin;
import member.MemberLogin;
import seller.SellerLogin;

public class SsangsinsaMain {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.println("==== M A I N ====");
			System.out.println("1. 관리자 로그인");
			System.out.println("2. 판매자 로그인");
			System.out.println("3. 회원 로그인");
			System.out.println("4. 회원가입");
			System.out.println("0. 종료");
			System.out.println("=================");
			System.out.print("입력 : ");
			String input = sc.nextLine();
			if(input.equals("1")) {
				AdminLogin adminLogin = new AdminLogin();
				adminLogin.login();
			} else if(input.equals("2")) {
				SellerLogin sellerLogIn = new SellerLogin();
				sellerLogIn.login();
			} else if(input.equals("3")){
				MemberLogin memberLogIn = new MemberLogin();
				memberLogIn.login();
			} else if (input.equals("4")) {
				MemberJoin memberJoin = new MemberJoin();
				memberJoin.memberJoin();
			} else if(input.equals("0")) {
				break;
			} else {
				System.out.println("올바른 번호를 입력해주세요!");
			}
		}
	}
}

