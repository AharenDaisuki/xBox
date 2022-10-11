package data;


public abstract class Client{    
    //private String name;
    private String email;
	private String phoneNo;
    private String password;
    
    public Client(String email_, String phoneNo_, String password_) {
    	this.email = email_;
    	this.phoneNo = phoneNo_;
    	this.password = password_;
    }
    
    public abstract double getDiscount();
    
    public abstract int getMaxBorrowedCount();
    
    public String getEmail() { return this.email; }
    
    public String getPhoneNo() { return this.phoneNo; }
    
    public String getPassword() { return password; }
}
