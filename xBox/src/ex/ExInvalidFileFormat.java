/**
 * 
 */
package ex;

import java.lang.Exception;

/**
 * @author lixiaoyang
 *
 */
public class ExInvalidFileFormat extends Exception{
	private static final long serialVersionUID = 2L;
	// constructor
	public ExInvalidFileFormat() { super("[Error] The file format is invalid!"); }
	public ExInvalidFileFormat(String errorMsg) { super(errorMsg); }
}
