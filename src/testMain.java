import seller.SellerRegisterDelivery;
import seller.SellerUser;

public class testMain {
	public static void main(String[] args) {
		SellerRegisterDelivery s = new SellerRegisterDelivery();
		SellerUser sellerUser = new SellerUser();
		sellerUser.setSeq(1);
		
		s.SellerDeliveryMain(sellerUser);
	}
	
	
}
