package admin;
//관리자 객체
public class AdminUser {
private int seq; 	//번호
private String ID; 	//아이디
private String PWD; //비밀번호

public int getSeq() {
	return seq;
}
public void setSeq(int seq) {
	this.seq = seq;
}
public String getID() {
	return ID;
}
public void setID(String iD) {
	ID = iD;
}
public String getPWD() {
	return PWD;
}
public void setPWD(String pWD) {
	PWD = pWD;
}

}
