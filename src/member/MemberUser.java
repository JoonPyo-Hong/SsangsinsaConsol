package member;

public class MemberUser {

	private int seq; // 회원번호
	private String id;	// 아이디
	private String pwd;	// 비밀번호
	private String name;	// 이름
	private String address;	// 주소
	private String regdate;	// 가입일
	private String ssn;	//주민번호

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
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

	
	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	
	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

}
