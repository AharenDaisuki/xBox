package cmd;

import java.util.ArrayList;
import utils.*;
import data.*;
import data.Record;

import java.util.Date;

public class CmdStoreRentable extends Undoable{
    private final int size = 100;
    private final boolean[] flag = new boolean[size];
    private final Record[] allRecords = new Record[size];
    private final Request[] allRequests = new Request[size];
    private final Rentable[] allRentables = new Rentable[size];
    private final RentableStatus[] allStatus = new RentableStatus[size];
    private Client client;
    private Date date;
    private int number;
    
    public void execute(String[] cmdLine,Client aClient){
        /*
         * [0:n-1 rentableId] [n:2n-1 flag] [2n:month]
        */
        RequestSearcher requestSearcher = RequestSearcher.getInstance();
        RequestManager requestManager = RequestManager.getInstance();
        RecordManager recordManager = RecordManager.getInstance();
        XBoxDate currDate = XBoxDate.getInstance();
        this.client = aClient;
        this.number = cmdLine.length / 2;
        this.date = currDate.getDayAfterNMonth(cmdLine[2*number+1]);
        for (int i = 0; i < number; ++i){
            allRequests[i] = requestSearcher.searchByKeyword(cmdLine[i]);
            allRentables[i] = allRequests[i].getRentable();
            allStatus[i] = allRentables[i].getStatus(); // remember
            if(cmdLine[number + i].equals("y")) {
                // TODO: use
                this.flag[i] = true;
                allRentables[i].setStatus(new RentableStatusOccupied(date, aClient));
                allRecords[i] = new Record(aClient, allRentables[i], date);
                recordManager.insert(allRecords[i]);
            }else if(cmdLine[number + i].equals("n")) {
                // TODO: not use
                this.flag[i] = false;
                allRentables[i].setStatus(new RentableStatusAvailable());
            }
            // TODO: remove request
            requestManager.removeRequest(allRequests[i]);
        }
        addUndo(this);
        clearList();
    }
	
    @Override
    public void redo(){      
        RecordManager recordManager = RecordManager.getInstance();
        RequestManager requestManager = RequestManager.getInstance();
        for(int i = 0; i < number; ++i) {
            if(flag[i]) {
                allRentables[i].setStatus(new RentableStatusOccupied(date, client));
                recordManager.insert(allRecords[i]);
            }else {
                allRentables[i].setStatus(new RentableStatusAvailable());
            }
            requestManager.removeRequest(allRequests[i]);
        }
        addUndo(this);
        
    }
    @Override
    public void undo(){
        RecordManager recordManager = RecordManager.getInstance();
        RequestManager requestManager = RequestManager.getInstance();
        for(int i = 0; i < number; ++i) {
            requestManager.newRequest(allRequests[i]);
            if(flag[i]) {
                recordManager.delete(allRecords[i]);
                allRentables[i].setStatus(allStatus[i]);
            }else {
                allRentables[i].setStatus(allStatus[i]);
            }
        }
        addRedo(this);
    }
}