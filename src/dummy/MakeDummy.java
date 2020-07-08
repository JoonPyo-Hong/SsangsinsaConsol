package dummy;

import java.sql.Connection;

import java.util.Random;

import common.DBUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class MakeDummy {

	public static Random rnd = new Random();

	public static void main(String[] args) {

		// make dummy
		memberDummy();
//		salesDummy();

	}

	private static void salesDummy() {
		int salesNum = 5000;

		for (int i = 0; i < salesNum; i++) {
			// 번호, 회원번호, 날짜 , default

			int date = rnd.nextInt(27) + 1;
			
			String regitDate = "20200" + String.valueOf(rnd.nextInt(6) + 1)
					+ (date < 10 ? String.valueOf("0" + date) : String.valueOf(date));
			int memberNum = rnd.nextInt(1000) + 1;
			regitDate = String.format("%d-%d-2020 %d:%d:00", rnd.nextInt(6)+1, rnd.nextInt(25)+1,rnd.nextInt(22)+1,rnd.nextInt(59)+1);
			System.out.println(regitDate);
			System.out.println(memberNum);
			System.out.println();
			// insert
			try {
				Connection conn = new DBUtil().open();
				Statement stat = conn.createStatement();
				String sql = String.format("insert into tbl_sale values (saleseq.nextVal,%d,to_date('%s','mm-dd-yyyy hh24:mi:ss'),default)", memberNum,
						regitDate);
				int result = stat.executeUpdate(sql);
				System.out.println(result);
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	private static void memberDummy() {
		int memberNum = 1000;

		String id = "";
		String pw = "";
		String name = "";
		String address = "";
		String regitDate = "";
		String ssn = "";

		for (int i = 0; i < memberNum; i++) {
			name = makeName();
			System.out.println(name);
			id = makeId();
			System.out.println(id);
			pw = makePw();
			System.out.println(pw);
			address = makeAddr();
			System.out.println(address);
			int date = rnd.nextInt(27) + 1;
			regitDate = String.format("%d-%d-2020 %d:%d:00", rnd.nextInt(6)+1, rnd.nextInt(25)+1,rnd.nextInt(22)+1,rnd.nextInt(59)+1);

			System.out.println(regitDate);
			ssn = makeSsn();
			System.out.println(ssn);
			try {
				Connection conn = new DBUtil().open();
				Statement stat = conn.createStatement();
				String sql = String.format(
						"insert into tbl_member values (memberSeq.nextVal,'%s','%s','%s','%s',to_date('%s','mm-dd-yyyy hh24:mi:ss'),'%s',default)", id,
						pw, name, address, regitDate, ssn);
				int result = stat.executeUpdate(sql);
				System.out.println(result);
				System.out.println();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private static String makeSsn() {
		String ssn = "";
		ssn += rnd.nextInt(10) + 85 + ""; // 년도
		int month = rnd.nextInt(12) + 1;
		ssn += String.format("%02d", month); // 월
		int[] maxDays = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		int day = rnd.nextInt(maxDays[month - 1]) + 1;
		ssn += String.format("%02d", day); // 일
		ssn += "-";
		ssn += rnd.nextInt(2) + 1;
		ssn += String.format("%06d", rnd.nextInt(1000000));

		return ssn;
	}

	private static String makeName() {
		// name = name1 + name2 + name2
		String[] name1 = { "김", "이", "박", "최", "백", "권", "윤", "장", "정", "신" };
		String[] name2 = { "동", "현", "지", "호", "진", "영", "태", "성", "민", "승", "환", "종", "선", "찬", "우", "식", "윤", "예",
				"혜", "대" };

		return name1[rnd.nextInt(name1.length)] + name2[rnd.nextInt(name2.length)] + name2[rnd.nextInt(name2.length)];
	}

	private static String makeId() {

		// 'a' -> 97, 'z' -> 122
		String name = "";
		String reName = "";

		for (int i = 0; i < rnd.nextInt(2) + 7; i++) {
			name += (char) (rnd.nextInt(26) + 97);
		}
		for (int i = 0; i < rnd.nextInt(2) + 3; i++) {
			name += rnd.nextInt(10);
		}

		return name;
	}

	private static String makePw() {
		String pw = "";
		for (int i = 0; i < rnd.nextInt(4) + 5; i++) {
			pw += (char) (rnd.nextInt(26) + 97);
		}
		for (int i = 0; i < rnd.nextInt(5) + 3; i++) {
			pw += rnd.nextInt(10);
		}
		return pw;
	}

	private static String makeAddr() {
		String address = "";
		String[] state = { "서울특별시", "인천광역시", "부산광역시", "대전광역시", "대구광역시", "울산광역시", "광주광역시", "경기도", "강원도", "충청북도", "충청남도",
				"전라남도", "전라북도", "경상북도", "경상남도" };
		String[] city = { "가평군", "고양시", "과천시", "광명시", "광주시", "구리시", "군포시", "남양주시", "동두천시", "부천시", "수원시", "안산시", "안성시",
				"성남시", "하남시", "포천시", "용인시", "의정부시" };
		String[] gu = { "종로구", "중구", "용산구", "성동구", "광진구", "동대문구", "중랑구", "성북구", "강북구", "도봉구", "노원구", "은평구", "서대문구",
				"마포구", "양천구", "강서구", "구로구", "금천구", "영등포구", "동작구", "관악구", "서초구", "강남구", "송파구", "강동구" };
		String[] load = { "고덕로", "장안로", "경인로", "호국로", "팔달로", "강남대로", "양재천로", "광평로", "밤고개로", "삼성로", "봉은사로", "선릉로", "역삼로",
				"테헤란로", "도곡로", "자곡로", "논현로", "언주로" };

		address += state[rnd.nextInt(state.length)] + " ";
		if (!address.contains("특별시") && !address.contains("광역시")) {
			address += city[rnd.nextInt(city.length)] + " ";
		}
		address += gu[rnd.nextInt(gu.length)] + " ";
		address += load[rnd.nextInt(load.length)] + " ";
		address += rnd.nextInt(1000) + 1;
		return address;
	}
}
