package cmd;

import java.util.ArrayList;
import data.*;
import java.text.SimpleDateFormat;  
import java.util.Date;  
import utils.*;

public class RequestBoxCommand extends Undoable{
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
        clientname of the reuqest,box/bag,date
        */
        RentableManager rentableManager=RentableManager.getInstance();
        RecordManager recordManager= RecordManager.getInstance();
        RequestManager requestManager= RequestManager .getInstance(); 
        RequestStorer requeststorer=RequestStorer.getInstance();
        ClientSearcher clientSearcher=ClientSearcher.getInstance();
        RentableAllocator rentableAllocator=RentableAllocator.getInstance();
        
        thisClient=clientSearcher.searchByClientEmail(cmdLine[0]);
        //reordManager 内在实现自动分布 
        Rentable rentable=  rentableAllocator.borrowRentable(thisClient,cmdLine[1],SystemDate.toDate(cmdLine[2]));
        rentableManager.lendOutRentable(rentable);
        recordManager.insert(thisClient,rentable,SystemDate.toDate(cmdLine[2])，);
        requestManager.newRequest(thisClient, rentable, new RequestAndUse(SystemDate.toDate(cmdLine[2]), thisClient));;
        addUndo(null);
        clearList();
    };
}
