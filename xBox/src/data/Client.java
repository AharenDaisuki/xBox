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
    
    public boolean verifyPassword(String password_) { return password_.equals(this.password); }
    
    // public String getPassword() { return password; } // TODO: 这是哪个小可爱写的
    
    @Override
    public String toString() { return String.format("%-40s%-8s", this.email, this.phoneNo); }
    
    public String toJSONString() { return "{\"email\":\""+email+"\",\"phoneNo\":\""+phoneNo+"\",\"password\":\""+password+"\"}";}
}
