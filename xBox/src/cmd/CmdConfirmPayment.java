package cmd;

import java.util.ArrayList;

import data.*;
import data.Record;

public class CmdConfirmPayment extends Undoable{
    private final int size = 100;
    private Request[] allRequests = new Request[size]; 
    public void execute(String[] cmdLine, Client aClient){
        /*
         * 
         *
         */
        RequestSearcher requestSearcher = RequestSearcher.getInstance();
        RecordSearcher recordSearcher = RecordSearcher.getInstance();
        RequestManager requestManager = RequestManager.getInstance();
        
        ArrayList<Request> requestList = requestSearcher.searchAllByKeyword(aClient);
        ArrayList<Record> recordList = recordSearcher.searchAllByKeyword(aClient.getEmail());
        // TODO: remove remaining request
        int cnt = 0;
        for(Request request : requestList) {
            allRequests[cnt++] = request;
            requestManager.removeRequest(request);
        }
        // TODO: compute amount
    }
    
    @Override
    public void undo(){
        RequestManager requestManager = RequestManager.getInstance();
        for(Request request : allRequests) {
            requestManager.newRequest(request);
        }
    }
    
    @Override
    public void redo(){
        RequestManager requestManager = RequestManager.getInstance();
        for(Request request : allRequests) {
            requestManager.removeRequest(request);
        }
    }
}
