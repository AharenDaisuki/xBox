package cmd;

import java.util.ArrayList;
import data.*;
import data.Record;

public class ReturnBoxRequestCommand extends Undoable{
    private static Client thisClient;
    @Override
    public void redo(){      
        addUndo(this);
        
    }
    @Override
    public void undo(){

        addRedo(this);
    }
    public void execute(String[] cmdLine){
        /*
        clientname of the reuqest,searchbytype
        */
        RentableManager rentableManager=RentableManager.getInstance();
        RecordManager recordManager= RecordManager.getInstance();
        RecordSearcher recordSearcher=RecordSearcher.getInstance();
        ClientSearcher clientSearcher=ClientSearcher.getInstance();
        RentableAllocator rentableAllocator=RentableAllocator.getInstance();
        
        thisClient=clientSearcher.searchByClientEmail(cmdLine[0]);
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
