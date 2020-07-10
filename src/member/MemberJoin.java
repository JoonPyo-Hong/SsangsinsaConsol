package member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import common.DBUtil;
import main.SsangsinsaMain;

public class MemberJoin {
	
	Connection conn = null;
    Statement stat = null;
    PreparedStatement pstat = null;
    DBUtil util = new DBUtil();
    ResultSet rs = null;
    
//	public static void main(String[] args) {
//		MemberJoin test = new MemberJoin();
//		test.memberJoin();
//	}
//		
	
	public void memberJoin() {
		Scanner scan = new Scanner(System.in);
		
		//회원가입 입력창
		System.out.println("======================");
		System.out.println("회원가입");
		System.out.print("이름 : ");
		String name = scan.nextLine();
		System.out.print("아이디(5~14글자 이내): ");
		String id = scan.nextLine();
		System.out.print("비밀번호(8~14글자 이내): ");
		String pwd = scan.nextLine();
		System.out.print("주민등록번호(ex.950101-1111111) : ");
		String ssn = scan.nextLine();
		System.out.print("주소(서울시 강남구 역삼동 58-78) : ");
		String address = scan.nextLine();
		System.out.println();
		
		MemberBasic member = new MemberBasic(id, pwd, name, address, ssn);
		
		
		checksignIn(member);
		
	}
	
	private void checksignIn(MemberBasic member) {
		Scanner scan = new Scanner(System.in);
		int checkId = 0;
		
		//이름 유효성 검사
		if(member.getName().matches("^[가-힣]{2,6}$")) {
			//아이디 유효성 검사
			if(member.getId().matches("^[a-z0-9]{5,15}$")) {
				// 패스워드 유효성 검사
				if(member.getPwd().matches("^[a-z0-9]{8,15}$")) {
					//주민등록번호 유효성 검사
					if(member.getSsn().matches("\\d{6}\\-[1-4]\\d{6}")) {
						
						try {
							conn = util.open();
							
							stat = conn.createStatement();
							//아이디 중복검사
							String sql1 = "SELECT ID FROM TBL_MEMBER";
							
							rs = stat.executeQuery(sql1);
							
							while(rs.next()){
								
								if(rs.getString("id").equals(member.getId())) {
									
									checkId++;
								} 
								
							}
							
							rs.close();
							stat.close();
							
							
							if( checkId == 0) {
								
							stat = conn.createStatement();
							
							String sql = "INSERT INTO TBL_MEMBER(SEQ,ID,PWD,NAME,ADDRESS,REGDATE,SSN) VALUES (MEMBERSEQ.NEXTVAL,'" + member.getId() + "','" + member.getPwd() + "','" + member.getName() + "', '" + member.getAddress()+"',"+"SYSDATE"+",'"+member.getSsn()+"')";
							
							stat.executeUpdate(sql);
							System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
							System.out.println(member.getName() +"님 회원가입을 축하드립니다.");
							
							stat.close();
							conn.close();
							} else {
								System.out.println("중복되는 아이디가 있습니다. 아이디를 다시 입력해주세요.");
							}
							while(true) {
								
							
							System.out.println("뒤로가려면 0을 입력하세요.");
							String backNum = scan.nextLine();
							
							if(backNum.equals("0")) {
								break;
							}
								
							}
							
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						
					}else {
						System.out.println("주민등록번호르 확인해주시기 바랍니다.");
					}
				} else {
					System.out.println("비밀번호는 8~14글자의 영어, 숫자를 입력해주세요.");
				}
				
			} else {
				System.out.println("아이디는 5~15글자의 영어, 숫자로 입력해주세요.");
			}
		} else {
			System.out.println("이름은 2~6글자의 한글로 입력해주세요.");
		}
	}
	
	
}
