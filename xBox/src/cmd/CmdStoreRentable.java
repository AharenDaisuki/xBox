package cmd;

import java.util.ArrayList;
import utils.*;
import data.*;
import data.Record;
import ex.ExEntryNotFound;

import java.util.Date;

/*
 * User command [Store *]
 * */

public class CmdStoreRentable extends Undoable{
    private final int size = 100;
    // private final boolean[] flag = new boolean[size];
    private final Record[] allRecords = new Record[size];
    private final Request[] allRequests = new Request[size];
    private final Rentable[] allRentables = new Rentable[size];
    private final RentableStatus[] allStatus = new RentableStatus[size];
    private final RentableStatus[] allNewStatus = new RentableStatus[size];
    // private Client client; // TODO: remove
    private Date date;
    private int number;
    
    public String execute(String[] cmdLine,Client aClient) throws ExEntryNotFound{
        /*
         * [0:n-1 rentableId] [n:month]
        */
        RequestSearcher requestSearcher = RequestSearcher.getInstance();
        RequestManager requestManager = RequestManager.getInstance();
        RecordManager recordManager = RecordManager.getInstance();
        XBoxDate currDate = XBoxDate.getInstance();
        // this.client = aClient;
        
        String ret = "[stored list]\n";
        
        this.number = cmdLine.length-1; // {id0, id1, 2}
        this.date = currDate.getDayAfterNMonth(cmdLine[number]);
        // used
        for (int i = 0; i < number; ++i){
                // remember status
            allRequests[i] = requestSearcher.searchByKeyword(cmdLine[i]); // TODO: no such request
            allRentables[i] = allRequests[i].getRentable();
            allRecords[i] = new Record(aClient, allRentables[i], date);
            allStatus[i] = allRentables[i].getStatus(); // remember
            allNewStatus[i] = new RentableStatusOccupied(date, aClient);
            allRentables[i].setStatus(allNewStatus[i]);
            // manager
            recordManager.insert(allRecords[i]);
            requestManager.removeRequest(allRequests[i]);
            ret += String.format("> %s will be stored for %s\n", allRentables[i].getId(), aClient.getEmail());
        }
        // not used
        ret += "[remaining list]\n";
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
        String ret = "";
        RecordManager recordManager = RecordManager.getInstance();
        RequestManager requestManager = RequestManager.getInstance();
        for(int i = 0; i < number; ++i) {
            allRentables[i].setStatus(allNewStatus[i]);
            recordManager.insert(allRecords[i]);
            requestManager.removeRequest(allRequests[i]);
            ret += String.format("> store %s\n", allRentables[i].getId());
        }
        addUndo(this);
        return ret;
    }
    
    @Override
    public String undo(){
        String ret = "";
        RecordManager recordManager = RecordManager.getInstance();
        RequestManager requestManager = RequestManager.getInstance();
        for(int i = 0; i < number; ++i) {
            requestManager.newRequest(allRequests[i]);
            recordManager.insert(allRecords[i]);
            allRentables[i].setStatus(allStatus[i]);
            ret += String.format("> store %s\n", allRentables[i]);
        }
        addRedo(this);
        return ret;
    }
}