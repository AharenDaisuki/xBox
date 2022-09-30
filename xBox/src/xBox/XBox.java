/**
 * 
 */
package xBox;

/**
 * @author xyli45
 * 
 * Description:
 * User interfaces are provided here. 
 * 
 */
public class XBox {
	private static XBox instance = new XBox();
	
	private XBox() {}
	
	public static XBox getInstance() {
		return instance;
	}
	/*example*/
	// public void interface1() {}
}
