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
	
	// register TODO: DONE
	public String register(String[] cmdLine) throws ExAccountExists{
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
	
	// login TODO: DONE
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
	
	// summary all rentables
	public String summary(String[] cmdLine) {
	    String ret = "Summary:\n";
	    
	    ret += String.format("%-15s%-30s\n", "[ID]", "[STATUS]");
	    // System.out.printf("%-7s%-50s\n", "[ID]", "[STATUS]");
	    RequestSearcher requestSearcher = RequestSearcher.getInstance();
	    RecordSearcher recordSearcher = RecordSearcher.getInstance();
	    
	    ArrayList<Request> allRequests = requestSearcher.searchAllByKeyword(user);
	    //ret += "My requests:\n";
	    for(Request request : allRequests) {
	        ret += String.format("%s\n", request.getRentable().toString());
	    }
	    
	    ArrayList<Record> allRecords = recordSearcher.searchAllByKeyword(user.getEmail());
	    //ret += "My records:\n";
	    for(Record record : allRecords) {
	        ret += String.format("%s\n", record.getRentable().toString());
	    }
	    return ret;
	}
	
	// undo
	public String undo() {
	    String ret = "[Undo]\n";
	    ret += Undoable.undoCmd();
	    return ret;
	}
	
	// redo
	public String redo() {
	    String ret = "[Redo]\n";
	    Undoable.redoCmd();
	    return ret;
	}
	
	// request rentable
	public String request(String[] cmdLine) {
        String ret = (new CmdRequestRentable()).execute(cmdLine, user) + "\n";
        ret += summary(null);
        return ret; // TODO: sorting options
	}
	
	// store rentable
	public String store(String[] cmdLine) throws ExEntryNotFound {
	    String ret = (new CmdStoreRentable()).execute(cmdLine, user) + "\n";
	    ret += summary(null);
	    return ret;
	}
	
	// return rentable
	public String unload(String[] cmdLine) throws ExEntryNotFound {
	    String ret = (new CmdRequestReturn()).execute(cmdLine, user) + "\n";
	    ret += summary(null);
	    return ret;
	}
}
