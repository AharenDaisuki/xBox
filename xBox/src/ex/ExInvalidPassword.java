/**
 * 
 */
package ex;

/**
 * @author lixiaoyang
 * @brief ExInvalidPassword
 * 
 * Exception for invalid password
 */
public class ExInvalidPassword extends Exception{
    private static final long serialVersionUID = 4L;
    // constructor
    public ExInvalidPassword() { super("[Error] The password is invalid!"); }
    public ExInvalidPassword(String errorMsg) { super(errorMsg); }
}
