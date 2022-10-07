package cmd;

import java.util.ArrayList;

import data.*;
public class AdminReturnApprove extends Undoable{
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
         * adminName of the reuqest,clientName
        */
        if(cmdLine[0]=="ubox@gmail.com"){
            RequestSearcher requestSearcher=RequestSearcher.getInstance();
            Client thisClient=clientSearcher.searchByClientEmail(cmdLine[0]);
            ArrayList<Request> request= requestSearcher.searchByClient(thisClient);
            for (Request request2:request){
                request2.getTarget().changeRentableStatus(request2.getRentable());;
                requestManager.removeRequest(request2);
            }
        }
        else{
            
        }
    }
}
