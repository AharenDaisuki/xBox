/**
 * 
 */
package data;

/**
 * @author xyli45
 *
 * Description: 
 * This is an interface for clients to implement. (i.e., CSSAUG student, GAC student)
 */

public abstract class Client implements Comparable<Client>{    
    private String id;
    private String name;
    private String email;
	  private String phoneNo;
    private int borrowedCount; //we can set max borrowed later on
}
