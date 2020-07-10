package ssangsinsa;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class outerInfo {

   public static void main(String[] args) {
	   
	   List<Brand> list = new ArrayList<Brand>();

	   /*
	    1. 브랜드 이름 신경쓰지 말고 우선 수집 (페이지 10까지)
	    2. 전부 수집된 데이터에서 중복검사를 실시하고 중복된 사항은 제거 하는 코드 작성하고
	    3. 그리고 sql문 넣으면 에러가 안나지않을까? 어차피 컬럼에 unique constraint는 뺄수없으니까
	    
	    */
	   
       // 무신사스토어 > 상의 카테고리 > %s 페이지
//       String url = String.format(
//             "https://store.musinsa.com/app/items/lists/001/?category=&d_cat_cd=001&u_cat_cd=&brand=&sort=pop&sub_sort=&display_cnt=90&page=%s&page_kind=category&list_kind=small&free_dlv=&ex_soldout=&sale_goods=&exclusive_yn=&price=&color=&a_cat_cd=&sex=&size=&tag=&popup=&brand_favorite_yn=&goods_favorite_yn=&blf_yn=&campaign_yn=&bwith_yn=&price1=&price2=",
//             6);
       
       // 무신사스토어 > 하의 카테고리 > %s 페이지
//       String url = String.format(
//    		   "https://store.musinsa.com/app/items/lists/003/?category=&d_cat_cd=003&u_cat_cd=&brand=&sort=pop&sub_sort=&display_cnt=90&page=%s&page_kind=category&list_kind=small&free_dlv=&ex_soldout=&sale_goods=&exclusive_yn=&price=&color=&a_cat_cd=&sex=&size=&tag=&popup=&brand_favorite_yn=&goods_favorite_yn=&blf_yn=&campaign_yn=&bwith_yn=&price1=&price2=",
//    		   6);
       
       // 무신사스토어 > 아우터 카테고리 > %s 페이지
//       String url = String.format(
//    		   "https://store.musinsa.com/app/items/lists/002/?category=&d_cat_cd=002&u_cat_cd=&brand=&sort=pop&sub_sort=&display_cnt=90&page=%s&page_kind=category&list_kind=small&free_dlv=&ex_soldout=&sale_goods=&exclusive_yn=&price=&color=&a_cat_cd=&sex=&size=&tag=&popup=&brand_favorite_yn=&goods_favorite_yn=&blf_yn=&campaign_yn=&bwith_yn=&price1=&price2=",
//    		   6);
       
       // 무신사스토어 > 신발 카테고리 > %s 페이지
//       String url = String.format(
//    		   "https://store.musinsa.com/app/items/lists/005/?category=&d_cat_cd=005&u_cat_cd=&brand=&sort=pop&sub_sort=&display_cnt=90&page=%s&page_kind=category&list_kind=small&free_dlv=&ex_soldout=&sale_goods=&exclusive_yn=&price=&color=&a_cat_cd=&sex=&size=&tag=&popup=&brand_favorite_yn=&goods_favorite_yn=&blf_yn=&campaign_yn=&bwith_yn=&price1=&price2=",
//    		   6);
       
       // 무신사스토어 > 액세서리 카테고리 > %s 페이지
       String url = String.format(
    		   "https://store.musinsa.com/app/items/lists/011/?category=&d_cat_cd=011&u_cat_cd=&brand=&sort=pop&sub_sort=&display_cnt=90&page=%s&page_kind=category&list_kind=small&free_dlv=&ex_soldout=&sale_goods=&exclusive_yn=&price=&color=&a_cat_cd=&sex=&size=&tag=&popup=&brand_favorite_yn=&goods_favorite_yn=&blf_yn=&campaign_yn=&bwith_yn=&price1=&price2=",
    		   6);

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
          
          //상품명
//          Elements productName = doc.select("span.product_title > span");         
          //상품가격
//          Elements productPrice = doc.select("span#goods_price");         
          //이미지주소
//          Elements productImage = doc.select("div.product-img img");         
          //브랜드정보
          Elements sallerInfo = doc.select("div.sallerinfo_detail_section > div > dl > dd");         
          //브랜드주소
          Elements sallerAddress = doc.select("div.sallerinfo_detail_section_addr > dl > dd");
          //상품리뷰
//          Elements reviewTag = doc.select("div.pContent_text > span");
         
         //상품명
//          for (Element el : productName) {
//         	 
//         	 if (el.toString().contains("product_title_eng") 
//         			 || el.toString().contains("txt_delay") 
//         			 || el.toString().contains("txt_tit_product")) {
//         		 continue;
//         	 } else {
//         		 System.out.println("상품명 : " + el.toString().replace("<span>", "").replace("</span>", "").trim());
//         	 }
//         	 
//          }
          
          
          //상품가격
//          for (Element el : productPrice) {
//         	 System.out.println("가격 : " + el.toString()
//         			 .replace("<span class=\"product_article_price\" id=\"goods_price\">", "")
//         			 .replace("<del>", "")
//         			 .replace("</del>", "")
//         			 .replace("</span>", ""));
//          }
          
          
          //이미지주소
//          for (Element el : productImage) {
//         	 System.out.println("이미지주소 : " + el.absUrl("src"));
//          }
          
          //판매자 객체생성
          Brand brand = new Brand();
          
          int i = 1;
          String[] tmp = new String[2];
          //브랜드,상호명,전화번호
          for (Element el : sallerInfo) {
         	 
         	 	if (i == 1) {
	         		 tmp = el.text().split("/");
//	         		 System.out.println("상호명 : " + tmp[0].trim());
//	         		 System.out.println("법인명 : " + tmp[1].trim());
	         		 brand.setCompany(tmp[0].trim());
	         		 brand.setOwner(tmp[1].trim());
	         	 } else if (i == 2) {
//	         		 System.out.println("브랜드명 : " + el.text());
	         		 brand.setBrand(el.text());
	         	 } else if (i == 3) {
//	         		System.out.println("사업자번호 : " + el.text());
	         		 brand.setCompanyNum(el.text());
	         	 } else if (i == 5) {
	         		 if (el.text().isEmpty()) {
//	         			 System.out.println("전화번호: 미 기입");
	         			 brand.setTel("미 기입");
	         		 } else {
//	         			 System.out.println("전화번호 : " + el.text());
	         			brand.setTel(el.text());
	         		 }
	         	 } else {
	         		 i++;
	         		 continue;
	         	 }
         	 i++;
          }
          
          
          //브랜드주소
          for (Element el : sallerAddress) {
         	 
         	 if (el.text().startsWith("(")) {
         		 continue;
         	 } else {
//         		 System.out.println("브랜드 주소 : " + el.text());
         		 brand.setAddress(el.text());
         	 }
         	 
          }
          
          
//          int count = 1; //리뷰번호
//          //상품리뷰
//          for (Element el : reviewTag) {
//         	 
//         	 if (el.toString().contains("more-view")) {
//         		 continue;
//         	 } else {
//         		 System.out.println(count + ". " + el.toString().replace("<span class=\"content-review\">","").replace("<br>","").replace("</span>","").trim());        		 
//         		 count++;
//         	 }
//              
//          }
           
          //판매자 객체 리스트에 저장
//          list.add(brand);
//          System.out.println();
          
          if (list.size()==0) {
        	  list.add(brand);
          } else {
        	  boolean result = true;
	          for (int j=0; j<list.size(); j++) {
	        	  if (list.get(j).getBrand().equals(brand.getBrand())) {
	        		  result = false;
	        		  break;
	        	  }
	          }
	          if (result) {
	        	  list.add(brand);
	          }
        	  
          }
         
      }
       
       for (int i=0; i<list.size(); i++) {
    	   
    	   System.out.println(list.get(i).getBrand());
    	   System.out.println(list.get(i).getCompany());
    	   System.out.println(list.get(i).getAddress());
    	   System.out.println(list.get(i).getOwner());
    	   System.out.println(list.get(i).getTel());
    	   System.out.println(list.get(i).getCompanyNum());
    	   System.out.println();
    	   
       }
	       
       
      
	insertBrand(list);
	

   }

   private static void insertBrand(List<Brand> list) {
	   
	   Connection conn = null;
	   PreparedStatement  stat = null;
	   Statement stat2 = null;
	   ResultSet rs = null;
	   DBUtil util = new DBUtil();
	   List<String> nameList = new ArrayList<String>();
	   
	   try {
		   
		   conn = util.open();
		   
		   String sql2 = "SELECT NAME FROM TBL_COMPANY";
		   
		   stat2 = conn.createStatement();
		   
		   rs = stat2.executeQuery(sql2);
		   
		   while (rs.next()) {
//			   System.out.println(rs.getString("name"));
			   nameList.add(rs.getString("name"));
		   }
		   
		   rs.close();
		   stat2.close();
		   
		   String sql = "INSERT INTO TBL_COMPANY (SEQ, NAME, PWD, ADDRESS, OWNER, TEL, DELFLAG, COMPANY_NUM) "
				   + "VALUES (COMPANYSEQ.NEXTVAL, ?, ?, ?, ?, ?, DEFAULT, ?)";
		   
		   for (int i=0; i<list.size(); i++) {
			   
			   String pw = makePw();
			   
			   stat = conn.prepareStatement(sql);
			   
			   stat.setString(1, list.get(i).getBrand());
			   stat.setString(2, pw);
			   stat.setString(3, list.get(i).getAddress());
			   stat.setString(4, list.get(i).getCompany());
			   stat.setString(5, list.get(i).getTel());
			   stat.setString(6, list.get(i).getCompanyNum().replace("+", ""));
			   
			   int count = 0;
			   
			   for (int k=0; k<nameList.size(); k++) {
				   
				   if (nameList.get(k).equals(list.get(i).getBrand())) {
					   System.out.println("중복");
					   count++;
				   }
				   
			   }
			   
			   if (count==0) {
				   stat.executeUpdate();
				   System.out.println("추가 완료");
			   }
			   
			   stat.close();
			   
		   }
		   
		   conn.close();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
   }
   
   private static String makePw() {
	   
	   Random rnd = new Random();
		String pw = "";
		
		for (int i=0; i<8; i++) {
			
			if (i < 5) {
				pw += String.valueOf((char)((int)(rnd.nextInt(26)) + 97));
			} else {
				pw += String.valueOf(rnd.nextInt(10));
			}
			
		}
		
		return pw;
		
   }
   
}




