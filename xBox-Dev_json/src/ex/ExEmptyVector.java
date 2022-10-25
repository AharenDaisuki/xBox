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
public class ExEmptyVector extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// constructor
	public ExEmptyVector() { super("[dev Error] empty vector"); }
	public ExEmptyVector(String errorMsg) { super(errorMsg); }
}
