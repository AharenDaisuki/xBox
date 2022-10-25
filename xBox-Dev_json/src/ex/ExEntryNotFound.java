/**
 * 
 */
package ex;

/**
 * @author lixiaoyang
 *
 */
public class ExEntryNotFound extends Exception{
    private static final long serialVersionUID = 4L;
    // constructor
    public ExEntryNotFound() { super("[Error] Entry not found!"); }
    public ExEntryNotFound(String errorMsg) { super(errorMsg); }
}
