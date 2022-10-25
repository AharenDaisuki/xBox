/**
 * 
 */
package ex;

/**
 * @author lixiaoyang
 *
 */
public class ExNoSufficientRentable extends Exception {
    private static final long serialVersionUID = 3L;
    // constructor
    public ExNoSufficientRentable() { super("[Error] No sufficient items of such type!"); }
    public ExNoSufficientRentable(String errorMsg) { super(errorMsg); }
}
