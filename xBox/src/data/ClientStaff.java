package data;

public class ClientStaff extends Client{
    private int borrowedCount;

    public ClientStaff(String email, String phoneNo, String password) {
		super(email, phoneNo, password);
		this.borrowedCount = getMaxBorrowedCount(); 
	}
    
    @Override
    public void changeBorrowedCount(int number) {
        this.borrowedCount += number;
    }
    
    @Override
    public int getBorrowCount() { return this.borrowedCount; }

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
