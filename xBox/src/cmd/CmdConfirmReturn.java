package cmd;

import java.util.ArrayList;

import data.*;
import data.Record;

import ex.ExEntryNotFound;

/*
 * Admin command [Confirm checkin *]
 * 
 * */

public class CmdConfirmReturn extends Undoable{
    private int num;
    private final int size = 100;
    private final Rentable[] allRentables = new Rentable[size];
    private final RentableStatus[] allStatus = new RentableStatus[size];
    private final RentableStatus[] allNewStatus = new RentableStatus[size];
    private final Record[] allRecords = new Record[size];
    
    public String execute(String[] cmdLine,Client aClient) throws ExEntryNotFound{
        /*
         * [0:n-1 rentableId]
        */
        
        RecordSearcher recordSearcher = RecordSearcher.getInstance();
        RecordManager recordManager = RecordManager.getInstance();
        
        this.num = cmdLine.length;
        String ret = "[Checkin list]\n";
        
        for(int i = 0; i < num; ++i) {
            String rentableId = cmdLine[i];
            Record record = recordSearcher.searchByKeyword(rentableId);
            allRentables[i] = record.getRentable();
            if(!allRentables[i].getStatusStr().equals(RentableStatusPending.statusName)) {
                throw new ExEntryNotFound(String.format("[Error] Checkin notification [%s] not found", rentableId));
            }
            allStatus[i] = allRentables[i].getStatus();
            allNewStatus[i] = new RentableStatusAvailable();
            allRentables[i].setStatus(allNewStatus[i]);
            allRecords[i] = record;
            recordManager.delete(record);
        }
        return ret;
    }
    
    @Override
    public String redo(){
        RecordManager recordManager = RecordManager.getInstance();
        String ret = "";
        for(int i = 0; i < num; ++i) {
            ret += String.format("> Confirm checkin [%s]\n", allRentables[i].getId());
            allRentables[i].setStatus(allNewStatus[i]);
            recordManager.delete(allRecords[i]);
        }
        addUndo(this);
        return ret;
    }
    
    @Override
    public String undo(){
        RecordManager recordManager = RecordManager.getInstance();
        String ret = "";
        for(int i = 0; i < num; ++i) {
            ret += String.format("> Confirm confirmation [%s]\n", allRentables[i].getId());
            allRentables[i].setStatus(allStatus[i]);
            recordManager.insert(allRecords[i]);
        }
        addRedo(this);
        return ret;
    }
}
