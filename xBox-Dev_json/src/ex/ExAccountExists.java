/**
 * 
 */
package ex;

/**
 * @author lixiaoyang
 *
 */
public class ExAccountExists extends Exception{
    private static final long serialVersionUID = 5L;
    // constructor
    public ExAccountExists() { super("[Error] The email address has already been registered!"); }
    public ExAccountExists(String errorMsg) { super(errorMsg); }
}
