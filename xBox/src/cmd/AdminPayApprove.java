package cmd;

import java.util.ArrayList;

import data.*;
import utils.*;
public class AdminPayApprove extends Undoable{
    @Override
    public void undo(){

    }
    @Override
    public void redo(){

    }

    public void execute(String[] cmdLine,Client thisClient){
        /*
         * clientName，payOrNot
        */
        RequestSearcher requestSearcher=RequestSearcher.getInstance();
        
        if(cmdLine[2].equals("paied")){
            ArrayList<Request> requestList= requestSearcher.searchByClient(thisClient);
            for (Request request:requestList){
                Rentable rentable=request.getRentable();
                requestManager.removeRequest(request);
                recordManager.insert(thisClient,rentable,request.getTarget().getDueDate());
            }
        }else if(cmdLine[2].equals("unpaied")){

        }

    }
}
