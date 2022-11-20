/**
 * 
 */
package ex;

/**
 * @author lixiaoyang
 * @brief ExNoSufficientRentable
 * 
 * Exception for the case where items are not sufficient
 */

public class ExNoSufficientRentable extends Exception {
    private static final long serialVersionUID = 3L;
    // constructor
    public ExNoSufficientRentable() { super("[Error] No sufficient items of such type!"); }
    public ExNoSufficientRentable(String errorMsg) { super(errorMsg); }
}
