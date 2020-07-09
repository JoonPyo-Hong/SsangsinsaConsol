import member.MemberAs;
import member.MemberUser;

public class testMain {
	public static void main(String[] args) {
		MemberAs as = new MemberAs();
		MemberUser memberUser = new MemberUser();
		memberUser.setSeq(1);
		as.memberAsMain(memberUser);
		
	}
	
	
}
