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
 * @author lixiaoyang
 * 
 * @brief Admin Interface
 * 
 * Admin interfaces are defined here
 */

public class AdminInterfaces {
	private static AdminInterfaces instance = new AdminInterfaces();
	
	private AdminInterfaces() {}
	
	public static AdminInterfaces getInstance() {
		return instance;
	}
	
	// private Client admin;
	
	/*example*/
	// public String help() {}
	// int login(String[] params); // return uid
	// int register(String[] params); // return uid
	
    /**
    * 
    * @brief summaryAllItems()
    * 
    * summary of all items for admin
    */
	
	
	public String summaryAllItems() {
	    // type
	    String ret = "<Item Summary>:\n";
	    ret += String.format("%-15s%-15s\n", "[ID]", "[STATUS]");
	    RentableSearcher searcher = RentableSearcher.getInstance();
	    for(Rentable rentable : searcher.searchAllByKeyword("BOX")) {
	        ret += String.format("%s\n", rentable.toString());
	    }
	    for(Rentable rentable : searcher.searchAllByKeyword("BAG")) {
	         ret += String.format("%s\n", rentable.toString());
	    }
	    return ret + "\n";
	}
	
    /**
    * 
    * @brief summaryAllClients()
    * 
    * summary of all clients for admin
    */
	
	public String summaryAllClients() {
	    String ret = "<Client Summary>:\n";
	    ret += String.format("%-25s%-10s\n", "[EMAIL]", "[TEL]");
	    ClientSearcher searcher = ClientSearcher.getInstance();
	    for(Client client : searcher.searchAll()) {
	        ret += String.format("%s\n", client.toString());
	    }
	    return ret + "\n";
	}
	
    /**
    * 
    * @brief summaryAllRequests()
    * 
    * summary of all requests for admin
    */
	
	public String summaryAllRequests() {
        String ret = "<Request Summary>:\n";
        ret += String.format("%-15s%-25s%-10s\n", "[ID]", "[EMAIL]", "[DATE]");
        RequestSearcher searcher = RequestSearcher.getInstance();
        for(Request request : searcher.searchAll()) {
            ret += String.format("%s\n", request.toString());
        }
	    return ret;
	}
	
    /**
    * 
    * @brief summaryAllRecords()
    * 
    * summary of all records for admin
    */
	public String summaryAllRecords() {
        String ret = "<Record Summary>:\n";
        ret += String.format("%-15s%-15s%-25s%-10s\n", "[ID]", "[PAYMENT]", "[EMAIL]", "[DUE]");
        RecordSearcher searcher = RecordSearcher.getInstance();
        for(Record record : searcher.searchAll()) {
            ret += String.format("%s\n", record.toString());
        }
        return ret;
	}
	
    /**
    * 
    * @throws ExEntryNotFound 
    * @brief summaryClient()
    * 
    * summary of one certain client for admin
    * 
    * @exception client not found
    */
	
	public String summaryClient(String[] cmdLine) throws ExEntryNotFound {
	    // email
	    String ret = "";
	    ClientSearcher clientSearcher = ClientSearcher.getInstance();
	    Client client = clientSearcher.searchByKeyword(cmdLine[0]);
	    if(client == null) {
	        throw new ExEntryNotFound(String.format("[Error] <%s> not found!", cmdLine[0]));
	    }
	    ret += String.format("%-25s%-10s\n", "[EMAIL]", "[TEL]");
	    ret += String.format("%s\n", client.toString());
	    return ret;
	}
	
    /**
    * 
    * @throws ExEntryNotFound 
     * @brief summaryItem()
    * 
    * summary of one certain item for admin
    * 
    * @exception item not found
    */
	
	public String summaryItem(String[] cmdLine) throws ExEntryNotFound {
	    // id
	    String ret = "";
	    RentableSearcher rentableSearcher = RentableSearcher.getInstance();
	    Rentable rentable = rentableSearcher.searchByKeyword(cmdLine[0]);
	    if(rentable == null) {
	         throw new ExEntryNotFound(String.format("[Error] %s not found!", cmdLine[0]));
	    }
	    ret += String.format("%-15s%-15s\n", "[ID]", "[STATUS]");
	    ret += String.format("%s\n", rentable.toString());
	    return ret;
	}
	
    /**
    * 
    * @throws ExEntryNotFound 
     * @brief confirmPayment()
    * 
    * payment confirming interface for admin
    */
	
	public String confirmPayment(String[] cmdLine) throws ExEntryNotFound {
	    // email
	    ClientSearcher clientSearcher = ClientSearcher.getInstance();
	    Client client = clientSearcher.searchByKeyword(cmdLine[0]);
	    if(client == null) {
	        throw new ExEntryNotFound(String.format("[Error] <%s> not found!", cmdLine[0]));
	    }
	    String ret = (new CmdConfirmPayment()).execute(cmdLine, client);
	    return ret;
	}
	
    /**
    * 
    * @brief confirmReturn()
    * 
    * checkin request confirming interface for admin
    * 
    * @exception entry not found
    */
	
	public String confirmReturn(String[] cmdLine) throws ExEntryNotFound {
	    // email
        ClientSearcher clientSearcher = ClientSearcher.getInstance();
        Client client = clientSearcher.searchByKeyword(cmdLine[0]);
        if(client == null) {
            throw new ExEntryNotFound(String.format("[Error] <%s> not found!", cmdLine[0]));
        }
	    String ret = (new CmdConfirmReturn()).execute(cmdLine, client);
	    return ret;
	}
}
