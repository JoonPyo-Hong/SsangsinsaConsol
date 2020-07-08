package seller;

/**
 * 
 * @author 김동욱
 * 배송 정보를 가지고있는 메소드
 */
public class DeliveryInfo {
	private int seq;
	private String productName;
	private int quantity;
	private String regdate;
	private String destination;
	
	

	public DeliveryInfo(int seq, String productName, int quantity, String regdate, String destination) {
		this.seq = seq;
		this.productName = productName;
		this.quantity = quantity;
		this.regdate = regdate;
		this.destination = destination;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}
}
