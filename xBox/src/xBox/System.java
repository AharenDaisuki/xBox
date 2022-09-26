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
public class System {
	private static System instance = new System();
	
	private System() {}
	
	public static System getInstance() {
		return instance;
	}
	/*example*/
	// public void interface1() {}
}
