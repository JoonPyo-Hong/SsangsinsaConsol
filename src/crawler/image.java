package ssangsinsa;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class image {

    public static void main(String[] args) {
    		
    	// 무신사스토어 > 상의 > 반팔 티셔츠 > %s 페이지
		String url = String.format(
				"https://store.musinsa.com/app/items/lists/006/?category=&"
				+ "d_cat_cd=006&u_cat_cd=&brand=&sort=emt_high&sub_sort=&display"
				+ "_cnt=90&page=%s&page_kind=category&list_kind=small&free_dlv="
				+ "&ex_soldout=&sale_goods=&exclusive_yn=&price=&color=&a_cat_cd"
				+ "=&sex=&size=&tag=&popup=&brand_favorite_yn=&goods_favorite_yn="
				+ "&blf_yn=&campaign_yn=&bwith_yn=&price1=&price2="
				,	1); // 페이지 값만 변경하면 됨
	
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
	    
	    String webSiteURL = "";
	    
	    for (int i=0; i<productPage.size(); i++) {
	    	
	    	webSiteURL = productPage.get(i);
	    	
	    	try {
	    		
	    		//Connect to the website and get the html
	    		Document doc1 = Jsoup.connect(webSiteURL).get();
	    		
	    		//Get elements with img tag
	    		Elements img = doc1.select("div.product-img img");
	    		
	    		System.out.println(img);
	    		
	    		for (Element el : img) {
	    			
	    			//for each element get the srs url
	    			String src = el.absUrl("src");
	    			
	    			System.out.println("Image Found!");
	    			System.out.println("src attribute is : " + src);
	    			
	    			getImages(src);
	    			
	    		}
	    		
	    	} catch (IOException ex) {
	    		System.err.println("There was an error");
	    		Logger.getLogger(image.class.getName()).log(Level.SEVERE, null, ex);
	    	}
	    	
	    }
    	
    }

	private static void getImages(String src) throws IOException {
		
		String folderPath = "D:\\images";
 
        //Exctract the name of the image from the src attribute
        int indexname = src.lastIndexOf("/");
 
        if (indexname == src.length()) {
            src = src.substring(1, indexname);
        }
 
        indexname = src.lastIndexOf("/");
        String name = src.substring(indexname, src.length());
 
        System.out.println(name);
 
        //Open a URL Stream
        URL url = new URL(src);
        InputStream in = url.openStream();
 
        OutputStream out = new BufferedOutputStream(new FileOutputStream(folderPath+ name));
 
        for (int b; (b = in.read()) != -1;) {
            out.write(b);
        }
        
        out.close();
        in.close();
 
    }

}



