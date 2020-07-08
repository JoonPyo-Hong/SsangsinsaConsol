package admin;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import common.DBUtil;

public class AdminSaledlistManagement {


	public void memberSaledlistMain(AdminUser adminuser) {
		try {
			while(true) {
				System.out.println("======= 판매내역관리 =======");
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

	private void delete() {
		// print saled lsit
		view();

		ResultSet rs = null;
		Scanner scan = new Scanner(System.in);
		try {
			Connection conn = new DBUtil().open();
			Statement stat = conn.createStatement();

			// input period
			System.out.println("SEQ:");
			String seq = scan.nextLine();

			String sql = String.format("update tbl_salelist set delflag=1 where tbl_sale_seq = %s",seq);
			stat.executeUpdate(sql);


			// print

			conn.close();
			System.out.println("삭제가 완료되었습니다. \n 계속하시려면 엔터를 입력해주세요.");
			scan.nextLine();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void modify() {



	}

	private void view() {
		ResultSet rs = null;
		Scanner scan = new Scanner(System.in);
		try {
			Connection conn = new DBUtil().open();
			Statement stat = conn.createStatement();

			// input period
			System.out.println("시작년월일:");
			String startDate = scan.nextLine();
			System.out.println("종료년월일:");
			String endDate = scan.nextLine();

			String sql = String.format("select sl.tbl_sale_seq as saleSeq, c.name as cname, p.name as pname, pd.product_size as psize,sl.quantity as quantity, s.saleddate as sdate from tbl_salelist sl inner join tbl_product p on sl.tbl_product_seq = p.seq inner join tbl_company c on p.tbl_company_seq = c.seq inner join tbl_product_detail pd on pd.tbl_product_seq = p.seq inner join tbl_sale s on s.seq = sl.tbl_sale_seq where sl.delflag = 0 and s.saleddate > '%s' and s.saleddate < '%s' order by s.saleddate",startDate,endDate);
			rs = stat.executeQuery(sql);

			System.out.printf("[SEQ]\t\t[COMPANY]\t\t[PRODUCT]\t[SIZE]\t\t\t[QUANTITIY]\t[DATE]\n");

			// print
			while(rs.next()) {
				System.out.printf("%s\t%s\t%s\t%s\t\t%s\t%s\n",
						rs.getString("saleSeq"),
						rs.getString("cname"),
						rs.getString("pname"),
						rs.getString("psize"),
						rs.getString("quantity"),
						rs.getString("sdate").substring(0,10)
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
