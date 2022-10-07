package cmd;

import java.util.ArrayList;

import data.*;

public class AdminPayApprove extends Undoable{
    @Override
    public void undo(){

    }
    @Override
    public void redo(){

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
