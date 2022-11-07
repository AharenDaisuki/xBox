/**
 * 
 */
package ex;

import java.lang.Exception;

/**
 * @author lixiaoyang
 * @brief ExEmptyVector
 * 
 * Exception in developer level for illegal memory access
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
