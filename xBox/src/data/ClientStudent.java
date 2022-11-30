package data;

public class ClientStudent extends Client{
	private int borrowedCount;
    
	public ClientStudent(String email, String phoneNo, String password) {
		super(email, phoneNo, password);
		this.borrowedCount = this.getMaxBorrowedCount();
	}
	
	@Override
	public void changeBorrowedCount(int number) {
	    this.borrowedCount += number;
	}
	
	@Override
	public int getBorrowCount() { return this.borrowedCount; }

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