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
    // constructor
    public ExInvalidPassword() { super("[Error] The password is invalid!"); }
    public ExInvalidPassword(String errorMsg) { super(errorMsg); }
}
