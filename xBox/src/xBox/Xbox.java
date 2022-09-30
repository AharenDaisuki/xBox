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

public class Xbox {
	private static Xbox instance = new Xbox();
	/*example*/
	// public void interface1() {}
	public static Xbox GetSystem() {
		return instance;
	}
	public int GetUserName(String Name){
		if(Name.equals("djj")){
			return -1;
		}
		return 1;
	}
	public boolean CheckUser(String name,String password){
		//return getUser(name).getpassword? == password
		return true;
	}
	public void AddNewUser(String name,String password){
		return;
	}
	
}
