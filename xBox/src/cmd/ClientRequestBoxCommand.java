package cmd;

import java.util.ArrayList;
import data.*;
import java.text.SimpleDateFormat;  
import java.util.Date;  
import utils.*;

public class ClientRequestBoxCommand extends Undoable{
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
        RequestManager requestManager= RequestManager.getInstance(); 
        RentableAllocator rentableAllocator=RentableAllocator.getInstance();
        
        Rentable rentable= rentableAllocator.borrowRentable(thisClient,cmdLine[1],SystemDate.toDate(cmdLine[2]));
        requestManager.newRequest(thisClient, rentable,null);;
        addUndo(this);
        clearList();
    }

}
