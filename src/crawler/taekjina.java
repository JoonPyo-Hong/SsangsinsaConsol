package ssangsinsa;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class taekjina {

   public static void main(String[] args) {

      crawling();
      }

   private static void crawling() {    
      List<String> Text = new ArrayList<String>();
      List<String> Date = new ArrayList<String>();
                                       
      // 무신사스토어 > 상품 카테고리 > %s 페이지
      String url = String.format(
            "https://store.musinsa.com/app/items/lists/002/?category"
            + "=&d_cat_cd=002&u_cat_cd=&brand=&sort=emt_high&sub_sort"
            + "=&display_cnt=90&page=%s&page_kind=category&list_kind="
            + "small&free_dlv=&ex_soldout=&sale_goods=&exclusive_yn="
            + "&price=&color=&a_cat_cd=&sex=&size=&tag=&popup=&brand_"
            + "favorite_yn=&goods_favorite_yn=&blf_yn=&campaign_yn=&bwith"
            + "_yn=&price1=&price2=",
            1); // 페이지 값만 변경하면 됨

      Document doc = null;

      try {

         doc = Jsoup.connect(url).get();

      } catch (IOException e) {
         e.printStackTrace();
      }

      // get element
      Elements element = doc.select("div.list_img > a");
      String musinsaWebpage = "https://store.musinsa.com";

      // page list
      ArrayList<String> productPage = new ArrayList<String>();

      // add url on list
      for (Element el : element) {
         productPage.add(musinsaWebpage+el.attr("href"));
      }
      
      for(String page : productPage) {
         
         // connect on page
         try {
            doc = Jsoup.connect(page).get();
         } catch (Exception e) {
            e.printStackTrace();
         }
         
         //상품리뷰
         Elements reviewTag = doc.select("div.pContent_text > span");
         //리뷰날짜
         Elements reviewDate = doc.select("div.profile > p > span.date");
         
         
         
         //상품리뷰
         
         for (Element el : reviewTag) {
            
//             System.out.println(count + ". " + el.toString().replace("<span class=\"content-review\">","").replace("<br>","").replace("</span>","").trim());
             
             Text.add(el.toString().replace("<span class=\"content-review\">","").replace("<br>","").replace("</span>","").trim());
             
         }
         
         
         
         //리뷰 날짜
         
         for (Element el : reviewDate) {
            
//             System.out.println(el.toString().replace("<span class=\"date\">","").replace("</span>","").replace("<span class=\"date last\">","").trim());
             
             Date.add(el.toString().replace("<span class=\"date\">","").replace("</span>","").replace("<span class=\"date last\">","").replace(".", "").replace(":", "").replace(" ", "").trim() + "00");
         
         
         }
         
    } //productPage for문
      
      Connection conn = null;
      Statement stat = null;
      DBUtil util = new DBUtil();
      
      
      try {
    	  
    	  conn = util.open();
    	  stat = conn.createStatement();
    	  
    	  
    	  
    	  for(int i=0; i<60; i++) {
    		  
//    		  String quary = "INSERT INTO tbl_review(SEQ,CONTENT,REGDATE,TBL_SALE_SEQ, DELFLAG)"
//    		  		+ " VALUES (tbl_review_seq.NEXTVAL,'" + Text.get(i) +"'," + "to_date('"+ Date.get(i) + "','YYYYMMDDHH24MISS')" +","+ (i+1) + ","+ 0 + ")";
//    		  System.out.println(Text.get(i)+ "   " +Date.get(i));
//           
//    		  System.out.println();
//    		  stat.executeUpdate(quary);
    		  
    	  }
    	  
    	  stat.close();
    	  conn.close();
    	  
      } catch (Exception e) {
    	  e.printStackTrace();
      }
            
   }
        
}

