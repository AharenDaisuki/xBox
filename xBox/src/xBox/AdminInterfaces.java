/**
 * 
 */
package xBox;

import java.util.ArrayList;
import java.util.Arrays;
import data.*;
import cmd.CmdConfirmPayment;

/**
 * @author xyli45
 * 
 * Description:
 * User interfaces are provided here. 
 * 
 */
public class AdminInterfaces {
	private static AdminInterfaces instance = new AdminInterfaces();
	
	private AdminInterfaces() {}
	
	public static AdminInterfaces getInstance() {
		return instance;
	}
	
	private Client admin;
	
	/*example*/
	public void help() {}
	// int login(String[] params); // return uid
	// int register(String[] params); // return uid
	// 
	void summaryAllItems(String[] cmdLine) {
	    
	}
	
	void summaryAllClients(String[] cmdLine) {
	    
	}
	
	void summaryClient(String[] cmdLine) {
	    
	}
	
	void summaryItem(String[] cmdLine) {
	    
	}
	
	void searchClient(String[] cmdLine) {
	    
	}
	
	void confirmPayment(String[] cmdLine) {
	    (new CmdConfirmPayment()).execute(cmdLine, admin);
	}
}
