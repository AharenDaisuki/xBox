package cmd;

import java.util.ArrayList;

import data.*;
import data.Record;
public class AdminReturnApprove extends Undoable{
    @Override
    public void redo(){      
        addUndo(this);
        
    }
    @Override
    public void undo(){

        addRedo(this);
    }
    public void execute(String[] cmdLine,Client myClient){
        /*
         * [1+num]clientName,num*y or_
        */
        RecordManager recordManager= RecordManager.getInstance();
        ClientSearcher clientSearcher=ClientSearcher.getInstance();
        RequestSearcher requestSearcher=RequestSearcher.getInstance();
        RequestManager requestManager=RequestManager.getInstance();

        Client thisClient=clientSearcher.searchByClientEmail(cmdLine[0]);
        ArrayList<Request> requestList= requestSearcher.searchByClient(thisClient);
        int num=1;
        for (Request request:requestList){
            Rentable rentable=request.getRentable();
            if(cmdLine[num++].equals("y")){
                recordManager.delete(rentable);
                requestManager.removeRequest(request);
            }
            else if(cmdLine[num++].equals("_")){

            }
        }
    }
}
