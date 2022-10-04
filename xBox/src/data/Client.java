/**
 * 
 */
package data;

import java.util.ArrayList;

/**
 * @author xyli45
 *
 * Description: 
 * This is an interface for clients to implement. (i.e., CSSAUG student, GAC student)
 */

public abstract class Client implements Comparable<Client>{    
    private String name;
    private String email;
	private String phoneNo;
    private int borrowedCount; //we can set max borrowed later on
    private static ArrayList<Client> allClients = new ArrayList<>();
    
    // TODO: constructor TBD
    public Client(String name_, String email_, String phoneNo_) {
    	this.name = name_;
    	this.email = email_;
    	this.phoneNo = phoneNo_;
    	allClients.add(this);
    }
    
    public String getName() { return this.name; }
    
    public String getEmail() { return this.email; }
    
    public String getPhoneNo() { return this.phoneNo; }
    
    public int getBorrowedCount() { return this.borrowedCount; }
}
