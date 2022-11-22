package cmd;

import java.util.Date;

import data.*;  
import utils.XBoxDate;

import ex.ExNoSufficientRentable;

/**
*
* @brief user command: item request
* 
* This class implements request operation of item for user interface.
*  
* 
*/

public class CmdRequestRentable extends Undoable{
    private final int size = 100;
    // save data for undo & redo
    private Client user;
    //private String rentableType;
    private int requestN = 0;
    private int quotaN = 0;
    //private String monthN;
    private final Rentable[] allRentables = new Rentable[size];
    private final Request[] allRequests = new Request[size];
    private final RentableStatus[] allStatus = new RentableStatus[size];
    private final RentableStatus[] allNewStatus = new RentableStatus[size];
    
    /**
    * 
    * @param cmdLine command parameters [0: type] [1: quantity] [2: rent duration]
    * 
    * @param aClient the active client
    *  
    * @return string, log to be output
    */
    
    public String execute(String[] cmdLine, Client aClient) throws ExNoSufficientRentable {
        /*
         * [0:type] [1:number] [2:month] 
        */
        RequestManager requestManager = RequestManager.getInstance();
        RentableAllocator rentableAllocator = RentableAllocator.getInstance();
        XBoxDate systemDate = XBoxDate.getInstance();
            
        this.user = aClient;
        String rentableType = cmdLine[0];
        int len = Integer.parseInt(cmdLine[1]);
        if(len > aClient.getBorrowCount()) {
            throw new ExNoSufficientRentable(String.format("[Error] Only %d quotas remain for <%s>!", aClient.getBorrowCount(), aClient.getEmail()));
        }
        String monthN = cmdLine[2]; 
        String ret = "[request list]\n";
        Date dueDate = systemDate.getDayAfterNMonth(monthN);
        
        //try {
            for(int i = 0; i < len; ++i){
                // return rentable
                allRentables[i] = rentableAllocator.borrowRentable(user, rentableType);
                this.requestN++;
                this.quotaN++;
                aClient.changeBorrowedCount(-1);
                allStatus[i] = allRentables[i].getStatus();
                allNewStatus[i] = new RentableStatusRequested(user);
                // set status
                allRentables[i].setStatus(allNewStatus[i]);
                // new a request
                allRequests[i] = new Request(user, allRentables[i], dueDate);
                requestManager.newRequest(allRequests[i]);
                ret += String.format("> %-10s%tF\n", allRentables[i].getId(), allRequests[i].getDue());
            }
        //}catch(ExNoSufficientRentable ex) {
        //    ret += ex.getMessage() + "\n";
        //}
        addUndo(this); 
        clearList();
        return ret;
    }

    @Override
    public String undo() {
        String ret = "[Undo]\n";
        // System.out.println("undo");
        RequestManager requestManager = RequestManager.getInstance();
        // undo current command
        for(int i = 0; i < this.requestN; ++i) {
            // remove request
            requestManager.removeRequest(allRequests[i]);
            // change status
            allRentables[i].setStatus(allStatus[i]);
            // System.out.println(allRentables[i].getStatusStr());
            ret += String.format("> send request %s\n", allRentables[i].getId());
        }
        user.changeBorrowedCount(this.quotaN);
        // add this to redoList
        addRedo(this);
        return ret;
    }

    @Override
    public String redo() {
        String ret = "[Redo]\n";
        // System.out.println("redo");
        RequestManager requestManager = RequestManager.getInstance();
        // redo current command
        for(int i = 0; i < this.requestN; ++i) {
            allRentables[i].setStatus(allNewStatus[i]);
            // System.out.println(allRentables[i].getStatusStr());
            requestManager.newRequest(allRequests[i]);
            ret += String.format("> send request %s\n", allRentables[i].getId());
        }
        user.changeBorrowedCount(-this.quotaN);
        addUndo(this);
        return ret;
    }
}
