/**
 * 
 */
package data;

/**
 * @author xyli45
 *
 */
public class StudentGAC extends Client{
	private String sid;
	
	public StudentGAC(String name_, String email_, String phoneNo_) {
		super(name_, email_, phoneNo_);
		// TODO Auto-generated constructor stub
	}
	
	public String getName() {
		return String.format("%s(%s)", super.getName(), this.sid);
	}
	
	public String getSid() {
		return this.sid;
	}

	@Override
	public int compareTo(Client o) {
		// TODO Auto-generated method stub
		return 0;
	}
}
