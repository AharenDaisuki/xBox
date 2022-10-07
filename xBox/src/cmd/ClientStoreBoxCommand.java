package cmd;

import java.util.ArrayList;
import java.util.Arrays;
import utils.*;
import data.*;

public class ClientStoreBoxCommand extends Undoable{
    private Client myClient;
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
        numOfRequest,num*rentableId,num*yes or not used,date
        */
        RecordManager recordManager= RecordManager.getInstance();  
        RequestSearcher requestSearcher=RequestSearcher.getInstance();

        int num=Integer.parseInt(cmdLine[0]);
        for (int i=1;i<=num;i++){
            Request request= requestSearcher.searchByRentableId(cmdLine[i]);
            Rentable rentable=request.getRentable();
            if(cmdLine[num+i].equals("y")){
                request.setTarget(new RequestButNotUsed());
            }
            else if(cmdLine[num+i].equals("y")){
                request.setTarget(new RequestAndUse(SystemDate.toDate(cmdLine[2]),thisClient));
            }
            else ;
        }
        addUndo(this);
        clearList();
    }
	
}