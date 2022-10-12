package data;

public class ClientStaff extends Client{
    private int borrowedCount;
    
    public ClientStaff(String email, String phoneNo, String password) {
		super(email, phoneNo, password);
		this.borrowedCount = getMaxBorrowedCount(); 
	}
    
    public void changeBorrowedCount(int number) {
        this.borrowedCount += number;
    }

    @Override
    public double getDiscount() {
        return 0.8;
    }

    @Override
    public int getMaxBorrowedCount() {
        return 20;
    }
    
    // fees calculation would be done later
}
