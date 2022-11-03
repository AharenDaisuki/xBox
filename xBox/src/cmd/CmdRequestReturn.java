package cmd;

import data.*;
import data.Record;

import ex.ExEntryNotFound;

/*
 * User command [Return *]
 * 
 * */

public class CmdRequestReturn extends Undoable{
    private final int size = 100;
    private int num = 0;
    private final Rentable[] allRentables = new Rentable[size];
    private final RentableStatus[] allStatus = new RentableStatus[size];
    private final RentableStatus[] allNewStatus = new RentableStatus[size];
    
    public String execute(String[] cmdLine,Client aClient) throws ExEntryNotFound {
        /*
         * [0:n-1 rentableId]
        */
        RecordSearcher recordSearcher = RecordSearcher.getInstance();
        int len = cmdLine.length;
        String ret = "[Return list]\n";
        //try {
            for(int i = 0; i < len; ++i) {
                String rentableId = cmdLine[i];
                Record tgt = recordSearcher.searchByKeyword(rentableId);
                // TODO: not belongs to the client
                if(tgt == null || tgt.getClient() != aClient) {
                    new ExEntryNotFound(String.format("Rent record [%s] not found", rentableId));
                }
                // TODO: done
                if(tgt.getRentable().getStatusStr().equals(RentableStatusPending.statusName)) {
                    new ExEntryNotFound(String.format("Checkin notification[%s]'s been sent", rentableId));
                }
                this.num++;
                allRentables[i] = tgt.getRentable();
                allStatus[i] = allRentables[i].getStatus();
                allNewStatus[i] = new RentableStatusPending(aClient);
                allRentables[i].setStatus(allNewStatus[i]);
                ret += String.format("> send checkin notification [%s]\n", allRentables[i].getId());
            }
        //}catch(ExEntryNotFound ex) {
        //    ret += ex.getMessage() + "\n";
        //}
       
        addUndo(this);
        clearList();
        return ret;
    }
    
    @Override
    public String redo(){
        String ret = "[Redo]\n";
        for(int i = 0; i < num; ++i) {
            //if(allRentables[i] == null) {
            //    continue;
            //}
            allRentables[i].setStatus(allNewStatus[i]);
            ret += String.format("> send checkin notification [%s]\n", allRentables[i].getId());
        }
        addUndo(this);
        return ret;
    }
    
    @Override
    public String undo(){
        String ret = "[Undo]\n";
        for(int i = 0; i < num; ++i) {
            //if(allRentables[i] == null) {
            //    continue;
            //}
            allRentables[i].setStatus(allStatus[i]);
            ret += String.format("> send checkin notification [%s]\n", allRentables[i].getId());
        }
        addRedo(this);
        return ret;
    }
}
