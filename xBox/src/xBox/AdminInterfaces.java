/**
 * 
 */
package xBox;

import utils.StringUtils;
import java.util.ArrayList;
import java.util.Arrays;
import data.*;

/**
 * @author xyli45
 * 
 * Description:
 * User interfaces are provided here. 
 * 
 */
public class AdminInterfaces {
	private static AdminInterfaces instance = new AdminInterfaces();
	private RecordManager recordManager = RecordManager.getInstance();
	
	private AdminInterfaces() {}
	
	public static AdminInterfaces getInstance() {
		return instance;
	}
	
	/*example*/
	public void help() {}
	// int login(String[] params); // return uid
	// int register(String[] params); // return uid
	void summary(String[] params) {}
	void searchClient(String[] params) {}
	void searchRentable(String[] params) {}
}
