package data;

public class ClientStudent extends Client{

    
	public ClientStudent(String email, String phoneNo, String password) {
		super(email, phoneNo, password);
	}
	


    @Override
    public double getDiscount() {
        return 0.9;
    }

    @Override
    public int getMaxBorrowedCount() {
        return 10;
    }

    public String toJSONString()
    {
    	String str=super.toJSONString();
    	return str.substring(0,str.length()-1)+",\"type\":\"student\"}";
    }
    
	// fees calculation would be done later
}