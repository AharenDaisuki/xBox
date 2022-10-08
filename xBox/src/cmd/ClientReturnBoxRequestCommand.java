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
        RecordSearcher recordSearcher=RecordSearcher.getInstance();
        RequestManager requestManager=RequestManager.getInstance();
        RentableAllocator rentableAllocator=RentableAllocator.getInstance();

        ArrayList<Record> recordList=recordSearcher.searchByClientName(thisClient.getName());
        for(Record record:recordList){
            Rentable rentable=record.getRentable();
            rentableAllocator.returnRentable(rentable);
            requestManager.newRequest(thisClient, rentable, new RequestReturn(record.getDue(), thisClient));
        }
        addUndo(null);
        clearList();
    }


}
