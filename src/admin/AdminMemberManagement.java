package admin;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import common.DBUtil;

public class AdminMemberManagement {

	public void memberMngMain(AdminUser adminuser) {
		try {
			while(true) {
				System.out.println("======= 회원관리 =======");
				System.out.println("1. 조회");
				System.out.println("2. 수정");
				System.out.println("3. 삭제");
				System.out.println("0. 뒤로가기");
				System.out.println("==================");

				Scanner scan = new Scanner(System.in);

				String sel = scan.nextLine();
				if(sel.equals("1")) {
					// 조회
					view();
				}
				else if(sel.equals("2")) {
					// 수정
					modify();
				}
				else if(sel.equals("3")) {
					// 삭제
					delete();
				}
				else if(sel.equals("0")) {
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("번호를 다시 입력해주세요");
		}




	}
	private void modify() {
		 
		ResultSet rs = null;
		Scanner scan = new Scanner(System.in);
		try {
			Connection conn = new DBUtil().open();
			Statement stat = conn.createStatement();
			String sql = "select * from tbl_member where delflag =0";
			rs = stat.executeQuery(sql);

			System.out.printf("[ID]\t\t[PW]\t\t[NAME]\t[ADDRESS]\t\t\t[REGDATE]\t[SSN]\n");

			while(rs.next()) {
				System.out.printf("%s\t%s\t%s\t%s\t\t%s\t%s\n",
						rs.getString("id"),
						rs.getString("pwd"),
						rs.getString("name"),
						rs.getString("address"),
						rs.getString("regdate").substring(0,10),
						rs.getString("ssn")
						);
			}

			rs.close();
			conn.close();
			System.out.println("계속하시려면 엔터를 입력해주세요.");
			scan.nextLine();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	private void delete() {

		Scanner scan = new Scanner(System.in);

		try {
			Connection conn = new DBUtil().open();
			Statement stat = conn.createStatement();

			System.out.println("ID를 입력해주세요");
			System.out.println(":");
			String deleteId = scan.nextLine();

			String sql = String.format("update tbl_member set delflag=1 where id='%s'",deleteId);
			stat.executeUpdate(sql);
			System.out.println("삭제가 완료되었습니다. \n 계속하시려면 엔터를 입력해주세요.");
			scan.nextLine();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void view() {

		ResultSet rs = null;
		Scanner scan = new Scanner(System.in);
		try {
			Connection conn = new DBUtil().open();
			Statement stat = conn.createStatement();
			String sql = "select * from tbl_member where delflag =0";
			rs = stat.executeQuery(sql);

			System.out.printf("[ID]\t\t[PW]\t\t[NAME]\t[ADDRESS]\t\t\t[REGDATE]\t[SSN]\n");

			while(rs.next()) {
				System.out.printf("%s\t%s\t%s\t%s\t\t%s\t%s\n",
						rs.getString("id"),
						rs.getString("pwd"),
						rs.getString("name"),
						rs.getString("address"),
						rs.getString("regdate").substring(0,10),
						rs.getString("ssn")
						);
			}

			rs.close();
			conn.close();
			System.out.println("계속하시려면 엔터를 입력해주세요.");
			scan.nextLine();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
