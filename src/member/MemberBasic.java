package member;

public class MemberBasic {
	private String id;	//아이디
	private String pwd;	//비밀번호
	private String name;	//이름
	private String address;	//주소
	private String ssn;	//주민등록번호
	
	
	
	public MemberBasic(String id, String pwd, String name, String address, String ssn) {
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.address = address;
		this.ssn = ssn;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getSsn() {
		return ssn;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	
	
	
	
	
}
