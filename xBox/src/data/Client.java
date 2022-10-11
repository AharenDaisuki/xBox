package data;


public abstract class Client{    
    private String email;
	private String phoneNo;
    private String password;
    private int borrowedCount; //we can set max borrowed later on
    
    public Client(String email_, String phoneNo_, String password_) {
    	this.email = email_;
    	this.phoneNo = phoneNo_;
        this.password = password_;
    }
    
    public String getEmail() { return this.email; }
    
    public String getPhoneNo() { return this.phoneNo; }
    
    public int getBorrowedCount() { return this.borrowedCount; }
}
