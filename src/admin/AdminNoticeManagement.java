package admin;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import common.DBUtil;

public class AdminNoticeManagement {
	
	public void noticeMngMain(AdminUser adminuser) {
		try {
			while(true) {
				System.out.println("====== 공지관리 =======");
				System.out.println("1. 조회");
				System.out.println("2. 수정");
				System.out.println("3. 삭제");
				System.out.println("0. 뒤로가기");
				System.out.println("==================");

				Scanner scan = new Scanner(System.in);

				String sel = scan.nextLine();
				if(sel.equals("1")) {
					// 조회
					view(adminuser);
				}
				else if(sel.equals("2")) {
					// 수정
					modify(adminuser);
				}
				else if(sel.equals("3")) {
					// 삭제
					delete(adminuser);
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

	private void delete(AdminUser adminUser) {
		
		
		// 추가로 작성해야함
		ResultSet rs = null;
		Scanner scan = new Scanner(System.in);
		try {
			Connection conn = new DBUtil().open();
			Statement stat = conn.createStatement();
			
			System.out.println("삭제하고싶은 SEQ를 입력해주세요");
			System.out.println(":");
			String deleteSeq= scan.nextLine();
	
			String sql = String.format("update tbl_notice set delflag=1 where seq=%s",deleteSeq);
			rs = stat.executeQuery(sql);

			System.out.printf("[SEQ]\n[TITLE]\n[REGDATE]\n[CONTENT]\n\n");

			while(rs.next()) {
				System.out.printf("%s\n%s\n%s\n%s\n\n",
						rs.getString("seq"),
						rs.getString("title"),
						rs.getString("regdate").substring(1,10),
						rs.getString("content")
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


	private void modify(AdminUser adminUser) {
		
		ResultSet rs = null;
		Scanner scan = new Scanner(System.in);
		try {
			Connection conn = new DBUtil().open();
			Statement stat = conn.createStatement();
			String sql = String.format("select * from tbl_notice where tbl_admin_seq = %d",adminUser.getSeq());
			rs = stat.executeQuery(sql);

			System.out.printf("[SEQ]\n[TITLE]\n[REGDATE]\n[CONTENT]\n\n");

			while(rs.next()) {
				System.out.printf("%s\n%s\n%s\n%s\n\n",
						rs.getString("seq"),
						rs.getString("title"),
						rs.getString("regdate").substring(1,10),
						rs.getString("content")
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

	private void view(AdminUser adminUser) {

		ResultSet rs = null;
		Scanner scan = new Scanner(System.in);
		try {
			Connection conn = new DBUtil().open();
			Statement stat = conn.createStatement();
			String sql = String.format("select * from tbl_notice where tbl_admin_seq = %d",adminUser.getSeq());
			rs = stat.executeQuery(sql);

			System.out.printf("[SEQ]\n[TITLE]\n[REGDATE]\n[CONTENT]\n\n");

			while(rs.next()) {
				System.out.printf("%s\n%s\n%s\n%s\n\n",
						rs.getString("seq"),
						rs.getString("title"),
						rs.getString("regdate").substring(1,10),
						rs.getString("content")
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

