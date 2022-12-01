/**
 * 
 */
package ex;

/**
 * @author lixiaoyang
 * @brief ExInfoMissing
 * 
 * Exception for the missing personal info when signing in
 */
public class ExInfoMissing extends Exception{
    // constructor
    public ExInfoMissing() { super("[Error] Please fill in all blank fields!"); }
    public ExInfoMissing(String errorMsg) { super(errorMsg); }
}
