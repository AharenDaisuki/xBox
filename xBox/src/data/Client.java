package data;


public abstract class Client{    
    private String name;
    private String email;
	private String phoneNo;
    private int borrowedCount; //we can set max borrowed later on
    
    public Client(String name_, String email_, String phoneNo_) {
    	this.name = name_;
    	this.email = email_;
    	this.phoneNo = phoneNo_;
    }
    
    public String getName() { return this.name; }
    
    public String getEmail() { return this.email; }
    
    public String getPhoneNo() { return this.phoneNo; }
    
    public int getBorrowedCount() { return this.borrowedCount; }
}
