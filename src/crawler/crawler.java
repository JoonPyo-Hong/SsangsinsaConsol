//package crawler;
//
//import java.io.IOException;
//import java.sql.Connection;
//import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.Random;
//
//import javax.xml.ws.WebEndpoint;
//
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//import org.jsoup.select.Elements;
//
//import common.DBUtil;
//
//public class crawler {
//	
//	
//	public static void main(String[] args) {
//		for(int pageNumber=11; pageNumber<30; pageNumber++) {	
//			insertNotice(pageNumber);
//		}
//
//	}
//
//	private static void insertNotice(int pageNumber) {
//		
//		// 무신사스토어 > 상품 카테고리 > %s 페이지
//		String url = String.format("https://www.musinsa.com/index.php?m=news&p=%d", pageNumber); // 페이지 값만 변경하면 됨
//		ArrayList<String> pageList = new ArrayList<String>();
//		ArrayList<String> regtimeList = new ArrayList<String>();
//		ArrayList<String> titleList = new ArrayList<String>();
//		ArrayList<String> contentList = new ArrayList<String>();
//
//		ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
//		Document doc = null;
//
//		try {
//
//			doc = Jsoup.connect(url).get();
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//		// get href of each page
//		Elements element = doc.select("div.articleImg > a");
//
//		// get register time of article
//		String time = doc.select("span.date.division").text();
//
//		// add url on list
//		for (Element el : element) {
//			pageList.add("https://www.musinsa.com" + el.attr("href"));
//
//		}
//
//		for (int i = 0; i <= time.length(); i += 15) {
//			String tempTime = time.substring(i, i + 14);
//			String timeFormat = String.format("%s-%s-2020 %s:%s:00", tempTime.substring(3, 5), tempTime.substring(6, 8),
//					tempTime.substring(9, 11), tempTime.substring(12, 14));
//			regtimeList.add(timeFormat);
//		}
//
//		// page list
//
//		// title
//
//		for (String page : pageList) {
//			try {
//
//				doc = Jsoup.connect(page).get();
//
//				// title
//				String title = doc.select("meta[name=title]").attr("content");
//				titleList.add(title);
//				
//
//				try {
//					// content
////					String content = doc.select("div.articleImg").first().child(2).text();
//					 String content = doc.select("div.articleImg").text();
//					
//					contentList.add(content);
//
//				} catch (Exception e) {
//					// if content was null
//					e.printStackTrace();
//					
//				}
//
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//
//		// insert date into DB
//		try {
//
//			
//			Connection conn = new DBUtil().open();
//			Statement stat = conn.createStatement();
//			for (int i=0; i<pageList.size(); i++) {
//				
//				System.out.println(titleList.get(i));
//				System.out.println(regtimeList.get(i));
//				System.out.println(contentList.get(i));
//				
//				String sql = String.format("insert into tbl_notice values (noticeseq.nextVal,'%s',to_date('%s','mm-dd-yyyy hh24:mi:ss'),'%s',%d,default)",
//						titleList.get(i),regtimeList.get(i),contentList.get(i),new Random().nextInt(5)+1);
//				int result = stat.executeUpdate(sql);
//				
//				System.out.println(result);
//				System.out.println();
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//
//}
