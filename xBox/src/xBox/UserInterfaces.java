package xBox;

import java.util.ArrayList;

import data.*;
import data.Record;
import ex.ExNoSufficientRentable;
import cmd.*;

public class UserInterfaces {
	// singleton
    private static UserInterfaces instance = new UserInterfaces();
	
	private Client user; 
	
	private UserInterfaces() {}
	
	public static UserInterfaces getInstance() {
		return instance;
	}
	
	// user interfaces
	
	// register
	public void register(String[] cmdLine) {
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
	        // TODO: The email is registered
	    }
	}
	
	// login
	public void login(String[] cmdLine) {
	    // email
	    // password
	    String email = cmdLine[0];
	    String password = cmdLine[1];
	    ClientSearcher searcher = ClientSearcher.getInstance();
	    this.user = searcher.searchByKeyword(email);
	    
	    if(user == null) {
	        // TODO: no such user, please register first
	        return;
	    }
	    
	    if(!user.getPassword().equals(password)) {
	        // TODO: invalid password
	        return;
	    }
	}
	
	// summary
	public void summary(String[] cmdLine) {
	    // TODO: terminal IO for the time being
	    System.out.printf("%-7s%-50s\n", "[ID]", "[STATUS]");
	    RequestSearcher requestSearcher = RequestSearcher.getInstance();
	    RecordSearcher recordSearcher = RecordSearcher.getInstance();
	    ArrayList<Request> allRequests = requestSearcher.searchAllByKeyword(user);
	    for(Request request : allRequests) {
	        System.out.println(request.getRentable().toString());
	    }
	    ArrayList<Record> allRecords = recordSearcher.searchAllByKeyword(user.getEmail());
	    for(Record record : allRecords) {
	        System.out.println(record.getRentable().toString());
	    }
	}
	
	public void undo() {
	    Undoable.undoCmd();
	}
	
	public void redo() {
	    Undoable.redoCmd();
	}
	
	// request rentable
	public void request(String[] cmdLine) {
        (new CmdRequestRentable()).execute(cmdLine, user);
	}
	
	// store rentable
	public void store(String[] cmdLine) {
	    (new CmdStoreRentable()).execute(cmdLine, user);
	}
	// return rentable
}
