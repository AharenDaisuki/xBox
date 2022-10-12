/**
 * 
 */
package utils;

import java.util.Calendar;
import java.util.Date;

/**
 * @author lixiaoyang
 * Description: Date utility inherited from java.util.Date
 */
public class XBoxDate{
    // local calendar for date computation
    private static XBoxDate instance = new XBoxDate();
    private static Calendar localCalendar = Calendar.getInstance();
    //private Date internalDate;
    
    public static XBoxDate getInstance() { return instance; }
    
    private XBoxDate() {}
    
    // TODO: format
    // public static XBoxDate toDate(String dateString) {}
    
    /*
     * Parameters:
     * monthString - the month value
     * 
     * Returns:
     * date object
     * */
    
    public Date getDayAfterNMonth(String monthString) {
        int numMonth = Integer.parseInt(monthString);
        localCalendar.add(Calendar.MONTH, numMonth);
        return localCalendar.getTime();
    }
}
