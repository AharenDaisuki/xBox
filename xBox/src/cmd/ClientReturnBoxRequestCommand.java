package cmd;

import java.util.ArrayList;
import data.*;
import data.Record;

public class ClientReturnBoxRequestCommand extends Undoable{
    private Client thisClient;
    @Override
    public void redo(){      
        addUndo(this);
        
    }
    @Override
    public void undo(){

        addRedo(this);
    }
    public void execute(String[] cmdLine,Client thisClient){
        /*
        
        */
        RentableManager rentableManager=RentableManager.getInstance();
        RecordManager recordManager= RecordManager.getInstance();
        RecordSearcher recordSearcher=RecordSearcher.getInstance();
        RentableAllocator rentableAllocator=RentableAllocator.getInstance();
        
        ArrayList<Record> recordList=recordSearcher.searchByClientName(cmdLine[0]);
        for(Record record:recordList){
            Rentable rentable=record.getRentable();
            rentableAllocator.returnRentable(rentable);
            rentableManager.getBackRentable(rentable);
            recordManager.delete(rentable);
        }
        addUndo(null);
        clearList();
    }


}
