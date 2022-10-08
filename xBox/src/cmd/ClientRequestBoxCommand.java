package cmd;

import data.*;
import java.text.SimpleDateFormat;  
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
        [1+1+1]box/bag,number of box/bag,rentingMonth
        */
        RequestManager requestManager= RequestManager.getInstance(); 
        RentableAllocator rentableAllocator=RentableAllocator.getInstance();
        
        for(int i=0;i<Integer.parseInt(cmdLine[1]);i++){
            Rentable rentable= rentableAllocator.borrowRentable(thisClient,cmdLine[0],SystemDate.toDate(cmdLine[2]));
            requestManager.newRequest(thisClient, rentable,null);
        }

        addUndo(this);
        clearList();
    }

}
