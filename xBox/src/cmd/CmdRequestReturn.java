package cmd;

import java.util.ArrayList;

import data.*;
import data.Record;

import ex.ExEntryNotFound;

/*
 * User command [Return *]
 * 
 * */

public class CmdRequestReturn extends Undoable{
    private final int size = 100;
    private int num;
    private final Rentable[] allRentables = new Rentable[size];
    private final RentableStatus[] allStatus = new RentableStatus[size];
    private final RentableStatus[] allNewStatus = new RentableStatus[size];
    
    public String execute(String[] cmdLine,Client aClient) throws ExEntryNotFound{
        /*
         * [0:n-1 rentableId]
        */
        RecordSearcher recordSearcher = RecordSearcher.getInstance();
        this.num = cmdLine.length;
        
        String ret = "[Return list]\n";
        for(int i = 0; i < num; ++i) {
            String rentableId = cmdLine[i];
            Record targetRecord = recordSearcher.searchByKeyword(rentableId);
            // TODO: not belongs to the client
            if(targetRecord.getClient() != aClient) {
                new ExEntryNotFound(String.format("Rent record [%s] not found", rentableId));
            }
            allRentables[i] = targetRecord.getRentable();
            allStatus[i] = allRentables[i].getStatus();
            allNewStatus[i] = new RentableStatusPending(aClient);
            allRentables[i].setStatus(allNewStatus[i]);
            ret += String.format("> send checkin notification [%s]\n", allRentables[i].getId());
        }
        addUndo(null);
        clearList();
        return ret;
    }
    
    @Override
    public String redo(){
        String ret = "";
        for(int i = 0; i < num; ++i) {
            allRentables[i].setStatus(allNewStatus[i]);
            ret += String.format("> cancel checkin notification [%s]\n", allRentables[i].getId());
        }
        addUndo(this);
        return ret;
    }
    
    @Override
    public String undo(){
        String ret = "";
        for(int i = 0; i < num; ++i) {
            allRentables[i].setStatus(allStatus[i]);
            ret += String.format("> resend checkin notification [%s]\n", allRentables[i].getId());
        }
        addRedo(this);
        return ret;
    }
}
