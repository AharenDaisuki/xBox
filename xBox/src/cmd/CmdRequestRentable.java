package cmd;

import data.*;  
import utils.XBoxDate;

/**
 * @author
 *
 * Description: 
 * User command class for requesting rentable
 * Note that one can only request one type of rentable in one go
 *  
 */

public class CmdRequestRentable extends Undoable{
    private final int size = 10000;
    // save data for undo & redo
    private Client user;
    private String rentableType;
    private int requestN;
    private String monthN;
    private Rentable[] allRentables = new Rentable[size];
    private Request[] allRequests = new Request[size];
    
    public void execute(String[] cmdLine, Client aClient){
        /*
         * [0:type] [1:number] [2:month] 
        */
        RequestManager requestManager = RequestManager.getInstance();
        RentableAllocator rentableAllocator = RentableAllocator.getInstance();
        XBoxDate systemDate = XBoxDate.getInstance();
        
        this.user = aClient;
        this.rentableType = cmdLine[0];
        this.requestN = Integer.parseInt(cmdLine[1]);
        this.monthN = cmdLine[2]; 
        
        // TODO: handle exception
        for(int i = 0; i < requestN; ++i){
            // return rentable
            allRentables[i] = rentableAllocator.borrowRentable(user, rentableType);
            // set status
            allRentables[i].setStatus(new RentableStatusRequested(user));
            // new a request
            allRequests[i] = new Request(user, allRentables[i], systemDate.getDayAfterNMonth(monthN));
            requestManager.newRequest(allRequests[i]);
        }
        addUndo(this);
        clearList();
    }

    @Override
    public void undo() {
        RequestManager requestManager = RequestManager.getInstance();
        // undo current command
        for(int i = this.requestN-1; i>=0; --i) {
            // remove request
            requestManager.removeRequest(allRequests[i]);
            // change status
            allRentables[i].setStatus(new RentableStatusAvailable());
        }
        // add this to redoList
        addRedo(this);
    }

    @Override
    public void redo() {
        RequestManager requestManager = RequestManager.getInstance();
        // redo current command
        for(int i = 0; i < requestN; ++i) {
            allRentables[i].setStatus(new RentableStatusRequested(user));
            requestManager.newRequest(allRequests[i]);
        }
        addUndo(this);
    }

}
