package cmd;

import data.*;  
import utils.XBoxDate;

public class ClientRequestBoxCommand extends Undoable{
    @Override
    public void redo(){      
        addUndo(this);
        
    }
    @Override
    public void undo(){

        addRedo(this);
    }

    public void execute(String[] cmdLine, Client thisClient){
        /*
         * [0:type] [1:number] [2:month] 
        */
        RequestManager requestManager= RequestManager.getInstance(); 
        RentableAllocator rentableAllocator=RentableAllocator.getInstance();
        XBoxDate currDate = new XBoxDate();
        // TODO: handle exception
        for(int i=0; i<Integer.parseInt(cmdLine[1]); i++){
            Rentable rentable= rentableAllocator.borrowRentable(thisClient, cmdLine[0], currDate.getDayAfterNMonth(cmdLine[2]));
            requestManager.newRequest(thisClient, rentable, null);
        }

        addUndo(this);
        clearList();
    }

}
