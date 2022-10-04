package utils;

import data.Day;

public class SystemDate extends Day{

    private static SystemDate instance;

    private SystemDate(String sDay) { super(sDay); }

    public static SystemDate getInstance(){ return instance; }

    public static void createTheInstance(String s) {
	    if (instance==null)
	        instance = new SystemDate(s);
	    else
	        System.out.println("Cannot create one more system date instance.");
    }
}
