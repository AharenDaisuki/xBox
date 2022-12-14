/**
 * 
 */
package ex;

/**
 * @author lixiaoyang
 * @brief ExEntryNotFound
 * 
 * Exception for the case where entries are not found
 */

public class ExEntryNotFound extends Exception{
    // constructor
    public ExEntryNotFound() { super("[Error] Entry not found!"); }
    public ExEntryNotFound(String errorMsg) { super(errorMsg); }
}
