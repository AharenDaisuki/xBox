package cmd;

import java.util.ArrayList;
import data.*;
import java.text.SimpleDateFormat;  
import java.util.Date;  
import utils.*;

public class RequestBoxCommand extends Undoable{
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
        clientname of the reuqest,box/bag,date
        */
        RequestManager requestManager= RequestManager .getInstance(); 
        RentableAllocator rentableAllocator=RentableAllocator.getInstance();
        
        //reordManager 内在实现自动分布 
        Rentable rentable=  rentableAllocator.borrowRentable(thisClient,cmdLine[1],SystemDate.toDate(cmdLine[2]));
        //[change newRequest]
        requestManager.newRequest(thisClient, rentable, new RequestAndUse(SystemDate.toDate(cmdLine[2]), thisClient));;
        addUndo(null);
        clearList();
    }

}
