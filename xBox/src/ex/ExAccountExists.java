/**
 * 
 */
package ex;

/**
 * @author lixiaoyang
 * @brief ExAccountExists
 * 
 * Exception for the case where accounts already exist
 */
public class ExAccountExists extends Exception{
    private static final long serialVersionUID = 5L;
    // constructor
    public ExAccountExists() { super("[Error] The email address has already been registered!"); }
    public ExAccountExists(String errorMsg) { super(errorMsg); }
}
