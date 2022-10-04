/**
 * 
 */
package data;

/**
 * @author xyli45
 *
 */
public class StudentAUG extends Client{
	private String sid;
	private String wechatNo;
	private static double discount;
	
	public StudentAUG(String name_, String email_, String phoneNo_) {
		super(name_, email_, phoneNo_);
		// TODO Auto-generated constructor stub
	}
	
	public String getName() {
		return String.format("%s(%s)", super.getName(), this.sid); 
	}
	
	public String getSid() { 
		return this.sid; 
	}
	
	public String getWechatNo() {
		return this.wechatNo;
	}
	
	public double getDiscount() {
		return StudentAUG.discount;
	}

	@Override
	public int compareTo(Client o) {
		// TODO Auto-generated method stub
		return 0;
	}
}
