/**
 * 
 */
package data;

/**
 * @author lixiaoyang
 * 
 * @brief storer interface
 * interface provided for data structure to manage insertion and deletion operations
 */

public interface XBoxStorer<ElemType> {
    public void addEntry(ElemType entry); /// <add an entry
    public void delEntry(ElemType entry); /// <delete an entry
}
