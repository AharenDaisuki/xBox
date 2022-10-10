/**
 * 
 */
package data;

/**
 * @author lixiaoyang
 * Description: interfaces provided for manager class to manage the insertion and deletion of data entry,
 * inherited by *Storer
 */
public interface XBoxStorer<ElemType> {
    public void addEntry(ElemType entry); // add entry
    public void delEntry(ElemType entry); // delete entry
}
