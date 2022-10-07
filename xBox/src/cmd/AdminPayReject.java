package cmd;

import java.util.ArrayList;

import data.*;
public class AdminPayReject extends Undoable{
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
         *clientName
        */

            RequestSearcher requestSearcher=RequestSearcher.getInstance();
            Client thisClient=clientSearcher.searchByClientEmail(cmdLine[0]);
            ArrayList<Request> request= requestSearcher.searchByClient(thisClient);
            for (Request request2:request){
                request2=new Request(thisClient, ;null, RequestButNotUsed);

            }
    }
}
