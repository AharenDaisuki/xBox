package xBox;

import java.util.ArrayList;

import data.*;
import data.Record;

import ex.*;
import debug.DebugConfig;

import cmd.Undoable;
import cmd.CmdStoreRentable;
import cmd.CmdRequestReturn;
import cmd.CmdRequestRentable;

/**
 * @author lixiaoyang
 * 
 * @brief User Interface
 * 
 * User interfaces are defined here
 */

public class UserInterfaces {
	// singleton
    private static UserInterfaces instance = new UserInterfaces();
	
	private Client user; 
	
	private UserInterfaces() {}
	
	public static UserInterfaces getInstance() {
		return instance;
	}
	
	// debug
	public boolean verifyLoginUser(String email) {
	    if(DebugConfig.VERIFY_LOGIN_DEBUG_FLAG) {
	        return this.user.getEmail().equals(email);
	    }
	    return false;
	}
	
	public Client getUser() {
	    if(DebugConfig.GET_USER_DEBUG_FLAG) {
	        return this.user;
	    }
	    return null;
	}
	
	/*User Interface*/
	

    /**
    * 
    * @param cmdLine interface parameters [0:email] [1:phoneNo] [2:password] [3:type]
    * 
    * @exception Account exists
    * @exception Info missing
    *  
    * @return string, log to be output
    */
	
	public String register(String[] cmdLine) throws ExAccountExists, ExInfoMissing{
	    // email = user name
	    // phone number
	    // password
	    // type
	    String email = cmdLine[0]; // TODO: unique
	    String phoneNo = cmdLine[1];
	    String password = cmdLine[2];
	    String type = cmdLine[3];
	    ClientSearcher searcher = ClientSearcher.getInstance();
	    ClientManager manager = ClientManager.getInstance();
	    Client newClient = null;
	    if(email.equals("") || phoneNo.equals("") || password.equals("")) {
	        throw new ExInfoMissing();
	    }
	    if(searcher.searchByKeyword(email) == null) {
	        // TODO: The email is available
	        // TODO: hide
	        switch(type) {
	            case "student":
	                newClient = new ClientStudent(email, phoneNo, password);
	                break;
	            case "staff":
	                newClient = new ClientStaff(email, phoneNo, password);
	                break;
	            /*add new type*/
	        }
	        manager.insert(newClient);
	        return String.format("Register Success <%s>", email);
	    }else {
	        throw new ExAccountExists(String.format("[Error] Email address <%s> has been registered!", email));
	    }
	}
	
    /**
    * 
    * @param cmdLine interface parameters [0:email] [1:password]
    * 
    * @exception entry not found
    * @exception invalid password
    * 
    * @return string, log to be output
    */
	
	public String login(String[] cmdLine) throws ExEntryNotFound, ExInvalidPassword{
	    // email
	    // password
	    String email = cmdLine[0];
	    String password = cmdLine[1];
	    ClientSearcher searcher = ClientSearcher.getInstance();
	    Client user_ = searcher.searchByKeyword(email);
	    
	    if(user_ == null) {
	        // TODO: no such user, please register first
	        throw new ExEntryNotFound(String.format("[Error] No user <%s>, please register first!", email));
	    }
	    
	    if(!user_.verifyPassword(password)) {
	        // TODO: invalid password
	        throw new ExInvalidPassword();
	    }
	    // assign user
	    this.user = user_;
	    return String.format("Login Success <%s>", email);
	}
	
	
    /**
    * 
    * @brief summary
    * 
    * summary all items
    */
	
	public String summary(String[] cmdLine) {
	    String ret = "Summary:\n";
	    
	    ret += String.format("%-15s%-15s%-10s\n", "[ID]", "[STATUS]", "[DUE]");
	    // System.out.printf("%-7s%-50s\n", "[ID]", "[STATUS]");
	    RequestSearcher requestSearcher = RequestSearcher.getInstance();
	    RecordSearcher recordSearcher = RecordSearcher.getInstance();
	    
	    ArrayList<Request> allRequests = requestSearcher.searchAllByKeyword(user.getEmail());
	    //ret += "My requests:\n";
	    for(Request request : allRequests) {
	        ret += String.format("%s%tF\n", request.getRentable().toString(), request.getDue());
	    }
	    
	    ArrayList<Record> allRecords = recordSearcher.searchAllByKeyword(user.getEmail());
	    //ret += "My records:\n";
	    for(Record record : allRecords) {
	        ret += String.format("%s%tF\n", record.getRentable().toString(), record.getDue());
	    }
	    return ret;
	}
	
    /**
    * 
    * @brief undo
    * 
    * undo the very last command
    */
	
	public String undo() throws ExEmptyVector {
	    String ret = ">> Undo the following operations?\n";
	    //try {
            ret += Undoable.undoCmd();
        //} catch (ExEmptyVector ex) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        //    System.out.println(ex.getMessage());
        //}
	    return ret;
	}
	
    /**
    * 
    * @brief redo
    * 
    * redo the very last command
    * 
    */
	
	public String redo() throws ExEmptyVector {
	    String ret = ">> Redo the following operations?\n";
	    //try {
            ret += Undoable.redoCmd();
        //} catch (ExEmptyVector ex) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        //    System.out.println(ex.getMessage());
        //}
	    return ret;
	}
	
    /**
    * 
    * @brief request
    * 
    * user interface for requesting items
    */
	
	public String request(String[] cmdLine) throws ExNoSufficientRentable {
        String ret = (new CmdRequestRentable()).execute(cmdLine, user) + "\n";
        ret += summary(null);
        return ret; // TODO: sorting options
	}
	
    /**
    * 
    * @brief store
    * 
    * user interface for storing items
    */
	
	public String store(String[] cmdLine) throws ExEntryNotFound {
	    String ret = (new CmdStoreRentable()).execute(cmdLine, user) + "\n";
	    ret += summary(null);
	    return ret;
	}
	
    /**
    * 
    * @brief return
    * 
    * user interface for returning items
    */
	
	public String unload(String[] cmdLine) throws ExEntryNotFound {
	    String ret = (new CmdRequestReturn()).execute(cmdLine, user) + "\n";
	    ret += summary(null);
	    return ret;
	}
}
