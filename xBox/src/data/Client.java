package data;


public abstract class Client{    
    private String name;
    private String email;
	private String phoneNo;
    private String password;

    private int borrowedCount; //we can set max borrowed later on
    
    public Client(String name_, String password_) {
    	this.name = name_;
    	this.password = password_;
    }
    
    public String getName() { return this.name; }
    
    public String getEmail() { return this.email; }
    
    public String getPhoneNo() { return this.phoneNo; }
    
    public int getBorrowedCount() { return this.borrowedCount; }

    public Object getPassword() {
        return password;
    }
}
