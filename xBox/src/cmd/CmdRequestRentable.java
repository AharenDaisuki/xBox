package cmd;

import data.*;  
import utils.XBoxDate;

import ex.ExNoSufficientRentable;

/**
 * 
 *
 * User command: [Request *]
 * 
 *  
 */

public class CmdRequestRentable extends Undoable{
    private final int size = 100;
    // save data for undo & redo
    private Client user;
    //private String rentableType;
    private int requestN;
    //private String monthN;
    private final Rentable[] allRentables = new Rentable[size];
    private final Request[] allRequests = new Request[size];
    private final RentableStatus[] allStatus = new RentableStatus[size];
    
    public String execute(String[] cmdLine, Client aClient) {
        /*
         * [0:type] [1:number] [2:month] 
        */
        RequestManager requestManager = RequestManager.getInstance();
        RentableAllocator rentableAllocator = RentableAllocator.getInstance();
        XBoxDate systemDate = XBoxDate.getInstance();
            
        this.user = aClient;
        String rentableType = cmdLine[0];
        this.requestN = Integer.parseInt(cmdLine[1]);
        String monthN = cmdLine[2]; 
        String ret = "[request list]\n";
        
        try {
            for(int i = 0; i < requestN; ++i){
                // return rentable
                allRentables[i] = rentableAllocator.borrowRentable(user, rentableType);
                allStatus[i] = new RentableStatusRequested(user);
                // set status
                allRentables[i].setStatus(allStatus[i]);
                // new a request
                allRequests[i] = new Request(user, allRentables[i], systemDate.getDayAfterNMonth(monthN));
                requestManager.newRequest(allRequests[i]);
                ret += String.format("> %s\n", allRentables[i].getId());
            }
        }catch(ExNoSufficientRentable ex) {
            ret += ex.getMessage() + "\n";
        }
        addUndo(this);
        clearList();
        return ret;
    }

    @Override
    public String undo() {
        String ret = "[Undo]\n";
        RequestManager requestManager = RequestManager.getInstance();
        // undo current command
        for(int i = this.requestN-1; i>=0; --i) {
            // remove request
            requestManager.removeRequest(allRequests[i]);
            // change status
            allRentables[i].setStatus(new RentableStatusAvailable());
            ret += String.format("> cancel request %s\n", allRentables[i].getId());
        }
        // add this to redoList
        addRedo(this);
        return ret;
    }

    @Override
    public String redo() {
        String ret = "[Redo]\n";
        RequestManager requestManager = RequestManager.getInstance();
        // redo current command
        for(int i = 0; i < requestN; ++i) {
            allRentables[i].setStatus(allStatus[i]);
            requestManager.newRequest(allRequests[i]);
            ret += String.format("> resend request %s\n", allRentables[i].getId());
        }
        addUndo(this);
        return ret;
    }
}
