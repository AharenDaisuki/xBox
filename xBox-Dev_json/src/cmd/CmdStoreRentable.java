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
    // private final Date[] allDates = new Date[size];
    private int number;
    
    public String execute(String[] cmdLine,Client aClient){
        /*
         * [0:n-1 rentableId]
        */
        RequestSearcher requestSearcher = RequestSearcher.getInstance();
        RequestManager requestManager = RequestManager.getInstance();
        RecordManager recordManager = RecordManager.getInstance();
        
        String ret = "[stored list]\n";
        
        this.number = cmdLine.length; // {id0, id1}
        // used
        
        try {
            for (int i = 0; i < number; ++i){
                // remember status
                allRequests[i] = requestSearcher.searchByKeyword(cmdLine[i]); // TODO: no such request
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
        }catch(ExEntryNotFound ex) {
            ret += ex.getMessage() + "\n";
        }
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
        String ret = "";
        RecordManager recordManager = RecordManager.getInstance();
        RequestManager requestManager = RequestManager.getInstance();
        for(int i = 0; i < number; ++i) {
            if(allRequests[i] == null) {
                continue;
            }
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
            if(allRequests[i] == null) {
                continue;
            }
            requestManager.newRequest(allRequests[i]);
            recordManager.insert(allRecords[i]);
            allRentables[i].setStatus(allStatus[i]);
            ret += String.format("> store %s\n", allRentables[i]);
        }
        addRedo(this);
        return ret;
    }
}