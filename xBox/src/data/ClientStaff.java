package data;

public class ClientStaff extends Client{
    
    public ClientStaff(String email, String phoneNo, String password) {
		super(email, phoneNo, password);
	}
    
    @Override
    public double getDiscount() {
        return 0.8;
    }

    @Override
    public int getMaxBorrowedCount() {
        return 20;
    }
    
    public String toJSONString()
    {
    	String str=super.toJSONString();
    	return str.substring(0,str.length()-1)+",\"type\":\"staff\"}";
    }
    
    // fees calculation would be done later
}
