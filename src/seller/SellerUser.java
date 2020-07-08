package seller;

public class SellerUser {
	
	private int seq; //primary key
	private String companyName; //상호명
	private String pwd; //비밀번호
	private String address; //주소
	private String owner; //법인명
	private String tel; //전화번호
	private long companyNum; //사업자번호
	
	
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	public long getCompanyNum() {
		return companyNum;
	}
	public void setCompanyNum(long companyNum) {
		this.companyNum = companyNum;
	}

}
