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
public class XBoxDate extends Date {
    // local calendar for date computation
    private static Calendar localCalendar = Calendar.getInstance();
    
    // TODO: format
    // public static XBoxDate toDate(String dateString) {}
    
    /*
     * Parameters:
     * monthString - the month value
     * 
     * Returns:
     * date object
     * */
    
    public XBoxDate getDayAfterNMonth(String monthString) {
        int numMonth = Integer.parseInt(monthString);
        localCalendar.setTime(this);
        localCalendar.add(Calendar.MONTH, numMonth);
        return (XBoxDate) localCalendar.getTime(); // pray for my sin :(
    }
}
