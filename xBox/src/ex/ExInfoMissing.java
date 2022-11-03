/**
 * 
 */
package ex;

/**
 * @author lixiaoyang
 *
 */
public class ExInfoMissing extends Exception{
    private static final long serialVersionUID = 6L;
    // constructor
    public ExInfoMissing() { super("[Error] Please fill in all blank fields!"); }
    public ExInfoMissing(String errorMsg) { super(errorMsg); }
}
