package utils;

import data.Day;

public class SystemDate extends Day{

    private static SystemDate instance;

    private SystemDate(String sDay) { super(sDay); }

    public static SystemDate getInstance(){ return instance; }

    public static Day toDate(String date){
        int year=Integer.parseInt(date, 0, 3, 0);
        int month=Integer.parseInt(date, 4, 6, 0);
        int day=Integer.parseInt(date, 7, 9, 0);
        return new Day(year,month,day);
    }

    public static void createTheInstance(String s) {
	    if (instance==null)
	        instance = new SystemDate(s);
	    else
	        System.out.println("Cannot create one more system date instance.");
    }
}
