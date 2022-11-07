package cmd;

import java.util.ArrayList;
import utils.*;
import data.*;
import data.Record;
import ex.ExEntryNotFound;

import java.util.Date;

/**
 *
 * @brief user command: store
 * 
 * This class implements store operation for user interface.
 *  
 * 
 */

public class CmdStoreRentable extends Undoable{
    private final int size = 100;
    // private final boolean[] flag = new boolean[size];
    private final Record[] allRecords = new Record[size];
    private final Request[] allRequests = new Request[size];
    private final Rentable[] allRentables = new Rentable[size];
    private final RentableStatus[] allStatus = new RentableStatus[size];
    private final RentableStatus[] allNewStatus = new RentableStatus[size];
    // private Client client; // TODO: remove
    // private final Date[] allDates = new Date[size];
    private int number = 0;
    
    
    /**
    * 
    * @param cmdLine command parameters [0:n-1 item Id]
    * 
    * @param aClient the active client
    *  
    * @return string, log to be output
    */
    public String execute(String[] cmdLine,Client aClient) throws ExEntryNotFound{
        /*
         * [0:n-1 rentableId]
        */
        RequestSearcher requestSearcher = RequestSearcher.getInstance();
        RequestManager requestManager = RequestManager.getInstance();
        RecordManager recordManager = RecordManager.getInstance();
        
        String ret = "[stored list]\n";
        
        int len = cmdLine.length; // {id0, id1}
        // used
        
        //try {
            for (int i = 0; i < len; ++i){
                // remember status
                allRequests[i] = requestSearcher.searchByKeyword(cmdLine[i]); // TODO: no such request
                if(allRequests[i] == null || allRequests[i].getClient() != aClient) {
                    throw new ExEntryNotFound(String.format("Request[%s] is not found", cmdLine[i]));
                }
                if(allRequests[i].getRentable().getStatusStr().equals(RentableStatusOccupied.statusName)) {
                    throw new ExEntryNotFound(String.format("%s has been stored for <%s>", cmdLine[i], aClient.getEmail()));
                }
                this.number++;
                // allDates[i] = allRequests[i].getDue();
                allRentables[i] = allRequests[i].getRentable();
                allRecords[i] = new Record(aClient, allRentables[i], allRequests[i].getDue());
                allStatus[i] = allRentables[i].getStatus(); // remember
                allNewStatus[i] = new RentableStatusOccupied(allRequests[i].getDue(), aClient);
                allRentables[i].setStatus(allNewStatus[i]);
                // manager
                recordManager.insert(allRecords[i]);
                requestManager.removeRequest(allRequests[i]);
                ret += String.format("> %s will be stored for %s\n", allRentables[i].getId(), aClient.getEmail());
            }
        //}catch(ExEntryNotFound ex) {
        //    ret += ex.getMessage() + "\n";
        //}
        // not used
        ret += "[unused list]\n";
        ArrayList<Request> allNotUsed = requestSearcher.searchAllByKeyword(aClient);
        for(Request request : allNotUsed) {
            ret += String.format("> %s is requested but not used\n", request.getRentable().getId());
        }
        addUndo(this);
        clearList();
        return ret;
    }
	
    @Override
    public String redo(){
        String ret = "[Redo]\n";
        RecordManager recordManager = RecordManager.getInstance();
        RequestManager requestManager = RequestManager.getInstance();
        for(int i = 0; i < this.number; ++i) {
            // if(allRequests[i] == null) {
            //    continue;
            //}
            allRentables[i].setStatus(allNewStatus[i]);
            recordManager.insert(allRecords[i]); // insert record
            requestManager.removeRequest(allRequests[i]); // remove request
            ret += String.format("> store %s\n", allRentables[i].getId());
        }
        addUndo(this);
        return ret;
    }
    
    @Override
    public String undo(){
        String ret = "[Undo]\n";
        RecordManager recordManager = RecordManager.getInstance();
        RequestManager requestManager = RequestManager.getInstance();
        for(int i = 0; i < this.number; ++i) {
            //if(allRequests[i] == null) {
            //    continue;
            //}
            requestManager.newRequest(allRequests[i]); // insert request
            recordManager.delete(allRecords[i]); // remove record
            allRentables[i].setStatus(allStatus[i]);
            ret += String.format("> store %s\n", allRentables[i].getId());
        }
        addRedo(this);
        return ret;
    }
}