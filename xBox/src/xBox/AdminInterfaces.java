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
	// public String help() {}
	// int login(String[] params); // return uid
	// int register(String[] params); // return uid
	
	
	public String summaryAllItems() {
	    // type
	    String ret = "<Item Summary>:\n";
	    ret += String.format("%-15s%-30s\n", "[ID]", "[STATUS]");
	    RentableSearcher searcher = RentableSearcher.getInstance();
	    for(Rentable rentable : searcher.searchAllByKeyword("BOX")) {
	        ret += String.format("%s\n", rentable.toString());
	    }
	    for(Rentable rentable : searcher.searchAllByKeyword("BAG")) {
	         ret += String.format("%s\n", rentable.toString());
	    }
	    return ret + "\n";
	}
	
	public String summaryAllClients() {
	    String ret = "<Client Summary]>:\n";
	    ret += String.format("%-30s%-10s\n", "[EMAIL]", "[TEL]");
	    ClientSearcher searcher = ClientSearcher.getInstance();
	    for(Client client : searcher.searchAll()) {
	        ret += String.format("%s\n", client.toString());
	    }
	    return ret + "\n";
	}
	
	// summary requests
	public String summaryAllRequests() {
        String ret = "<Request Summary>:\n";
        ret += String.format("%-15s%-30s%-10s\n", "[ID]", "[EMAIL]", "[DATE]");
        RequestSearcher searcher = RequestSearcher.getInstance();
        for(Request request : searcher.searchAll()) {
            ret += String.format("%s\n", request.toString());
        }
	    return ret;
	}
	
	// summary records
	public String summaryAllRecords() {
        String ret = "<Record Summary>:\n";
        ret += String.format("%-15s%-15s%-30s%-10s\n", "[ID]", "[PAYMENT]", "[EMAIL]", "[DUE]");
        RecordSearcher searcher = RecordSearcher.getInstance();
        for(Record record : searcher.searchAll()) {
            ret += String.format("%s\n", record.toString());
        }
        return ret;
	}
	
	public String summaryClient(String[] cmdLine) {
	    // email
	    String ret = "";
	    ClientSearcher clientSearcher = ClientSearcher.getInstance();
	    Client client = clientSearcher.searchByKeyword(cmdLine[0]);
	    ret += String.format("%-30s%-10s\n", "[EMAIL]", "[TEL]");
	    ret += String.format("%s\n", client.toString());
	    return ret;
	}
	
	public String summaryItem(String[] cmdLine) {
	    // id
	    String ret = "";
	    RentableSearcher rentableSearcher = RentableSearcher.getInstance();
	    Rentable rentable = rentableSearcher.searchByKeyword(cmdLine[0]);
	    ret += String.format("%-15s%-30s\n", "[ID]", "[STATUS]");
	    ret += String.format("%s\n", rentable.toString());
	    return ret;
	}
	
	public String confirmPayment(String[] cmdLine) {
	    // email
	    ClientSearcher clientSearcher = ClientSearcher.getInstance();
	    Client client = clientSearcher.searchByKeyword(cmdLine[0]);
	    String ret = (new CmdConfirmPayment()).execute(cmdLine, client);
	    return ret;
	}
	
	public String confirmReturn(String[] cmdLine) throws ExEntryNotFound {
	    // email
        ClientSearcher clientSearcher = ClientSearcher.getInstance();
        Client client = clientSearcher.searchByKeyword(cmdLine[0]);
	    String ret = (new CmdConfirmReturn()).execute(cmdLine, client);
	    return ret;
	}
}
