package xBox;

import java.util.ArrayList;

import data.*;
import data.Record;
import ex.*;

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
	
	/*User Interface*/
	
	// register
	public void register(String[] cmdLine) throws ExAccountExists{
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
	    if(searcher.searchByKeyword(email) == null) {
	        // TODO: The email is available
	        Client newClient = null;
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
	    }else {
	        throw new ExAccountExists();
	    }
	}
	
	// login
	public void login(String[] cmdLine) throws ExEntryNotFound, ExInvalidPassword{
	    // email
	    // password
	    String email = cmdLine[0];
	    String password = cmdLine[1];
	    ClientSearcher searcher = ClientSearcher.getInstance();
	    Client user_ = searcher.searchByKeyword(email);
	    
	    if(user_ == null) {
	        // TODO: no such user, please register first
	        throw new ExEntryNotFound("[error] No such user, please register first!");
	    }
	    
	    if(!user_.getPassword().equals(password)) {
	        // TODO: invalid password
	        throw new ExInvalidPassword();
	    }
	    // assign user
	    this.user = user_;
	}
	
	// summary all rentables
	public String summary(String[] cmdLine) {
	    String ret = "Summary:\n";
	    
	    ret += String.format("%-7s%-50s\n", "[ID]", "STATUS");
	    // System.out.printf("%-7s%-50s\n", "[ID]", "[STATUS]");
	    RequestSearcher requestSearcher = RequestSearcher.getInstance();
	    RecordSearcher recordSearcher = RecordSearcher.getInstance();
	    ArrayList<Request> allRequests = requestSearcher.searchAllByKeyword(user);
	    for(Request request : allRequests) {
	        ret += String.format("%s\n", request.getRentable().toString());
	        //System.out.println(request.getRentable().toString());
	    }
	    ArrayList<Record> allRecords = recordSearcher.searchAllByKeyword(user.getEmail());
	    for(Record record : allRecords) {
	        ret += String.format("%s\n", record.getRentable().toString());
	        //System.out.println(record.getRentable().toString());
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
	public String request(String[] cmdLine) throws ExNoSufficientRentable {
        String ret = (new CmdRequestRentable()).execute(cmdLine, user);
        ret += summary(null);
        return ret; // TODO: sorting options
	}
	
	// store rentable
	public String store(String[] cmdLine) throws ExEntryNotFound {
	    String ret = (new CmdStoreRentable()).execute(cmdLine, user);
	    ret += summary(null);
	    return ret;
	}
	
	// return rentable
	public String unload(String[] cmdLine) throws ExEntryNotFound {
	    String ret = (new CmdRequestReturn()).execute(cmdLine, user);
	    ret += summary(null);
	    return ret;
	}
}
