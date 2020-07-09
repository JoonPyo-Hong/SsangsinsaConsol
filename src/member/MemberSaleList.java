package member;

public class MemberSaleList {
	private int seq;
	private String saledate;
	private String prodname;
	private int quantity;
	private String company;
	
	

	public MemberSaleList(int seq, String saledate, String prodname, int quantity, String company) {
		this.seq = seq;
		this.saledate = saledate;
		this.prodname = prodname;
		this.quantity = quantity;
		this.company = company;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getSaledate() {
		return saledate;
	}

	public void setSaledate(String saledate) {
		this.saledate = saledate;
	}

	public String getProdname() {
		return prodname;
	}

	public void setProdname(String prodname) {
		this.prodname = prodname;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

}
