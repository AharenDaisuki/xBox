/**
 * 
 */
package xBox;

import data.*;
import data.Record;
import ex.ExEntryNotFound;
import cmd.CmdConfirmPayment;
import cmd.CmdConfirmReturn;

/**
 * @author xyli45
 * 
 * Description:
 * User interfaces are provided here. 
 * 
 */
public class AdminInterfaces {
	private static AdminInterfaces instance = new AdminInterfaces();
	
	private AdminInterfaces() {}
	
	public static AdminInterfaces getInstance() {
		return instance;
	}
	
	private Client admin;
	
	/*example*/
	public String help() {
	    return "";
	}
	// int login(String[] params); // return uid
	// int register(String[] params); // return uid
	// 
	String summaryAllItems(String[] cmdLine) {
	    // type
	    String ret = "Summary all:\n";
	    ret += String.format("%-7s%-50s\n", "[ID]", "[STATUS]");
	    RentableSearcher searcher = RentableSearcher.getInstance();
	    String type = cmdLine[0];
	    for(Rentable rentable : searcher.searchAllByKeyword(type)) {
	        ret += String.format("%s\n", rentable.toString());
	    }
	    return ret;
	}
	
	String summaryAllClients(String[] cmdLine) {
	    String ret = "Summary all:\n";
	    ret += String.format("%-40s%-8s\n", "[EMAIL]", "[TEL]");
	    ClientSearcher searcher = ClientSearcher.getInstance();
	    for(Client client : searcher.searchAll()) {
	        ret += String.format("%s\n", client.toString());
	    }
	    return ret;
	}
	
	String summaryAllRequests(String[] cmdLine) {
        String ret = "Summary all:\n";
        ret += String.format("%-7s%-40s%-10s\n", "[ID]", "[EMAIL]", "[DATE]");
        RequestSearcher searcher = RequestSearcher.getInstance();
        for(Request request : searcher.searchAll()) {
            ret += String.format("%s\n", request.toString());
        }
	    return ret;
	}
	
	String summaryAllRecords(String[] cmdLine) {
        String ret = "Summary all:\n";
        ret += String.format("%-7s%-10s%-40s%tF\n", "[ID]", "[PAYMENT]", "[EMAIL]", "[DUE]");
        RecordSearcher searcher = RecordSearcher.getInstance();
        for(Record record : searcher.searchAll()) {
            ret += String.format("%s\n", record.toString());
        }
        return ret;
	}
	
	String summaryClient(String[] cmdLine) {
	    return "";
	}
	
	String summaryItem(String[] cmdLine) {
	    return "";
	}
	
	String confirmPayment(String[] cmdLine) {
	    String ret = (new CmdConfirmPayment()).execute(cmdLine, admin);
	    return ret;
	}
	
	String confirmReturn(String[] cmdLine) throws ExEntryNotFound {
	    String ret = (new CmdConfirmReturn()).execute(cmdLine, admin);
	    return ret;
	}
}
