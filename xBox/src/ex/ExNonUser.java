/**
 * 
 */
package ex;

import java.lang.Exception;

/**
 * @author xyli45
 * 
 * Description:
 * empty vector(?) exception: // add comment here
 *
 */
public class ExNonUser extends Exception{
	/**
	 * 
	 */
	// constructor
	public ExNonUser() { super("User and password not match or not exist"); }
	public ExNonUser(String errorMsg) { super(errorMsg); }
}
