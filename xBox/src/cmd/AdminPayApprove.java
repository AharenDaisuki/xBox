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

    public void execute(String[] cmdLine,Client myClient){
        /*
         *[1+number] clientName，number *payOrNot
        */
        ClientSearcher clientSearcher=ClientSearcher.getInstance();
        RequestSearcher requestSearcher=RequestSearcher.getInstance();
        RecordManager recordManager=RecordManager.getInstance();
        RequestManager requestManager=RequestManager.getInstance();
        /*
        Client thisClient=clientSearcher.searchByClientEmail(cmdLine[0]);
        ArrayList<Request> requestList= requestSearcher.searchByClient(thisClient);

        int num=1;
        for (Request request:requestList){
            if(cmdLine[num++].equals("y")){
                //recordManager.insert(thisClient,request.getRentable(),request.getTarget().getDueDate());
                requestManager.removeRequest(request);
            }
            else if(cmdLine[num++].equals("_")){
                
            }
        }*/
    }
}
