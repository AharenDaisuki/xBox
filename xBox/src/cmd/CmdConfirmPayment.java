package cmd;

import java.util.ArrayList;

import data.*;
import data.Record;

/**
*
* @brief admin command: confirm item request
* 
* This class implements request confirming operation of item for admin interface.
*  
* 
*/

public class CmdConfirmPayment extends Undoable{
    private final int size = 100;
    private final Request[] allRequests = new Request[size]; 
    
    public String execute(String[] cmdLine, Client aClient){
        /*
         * email
         *
         */
        RequestSearcher requestSearcher = RequestSearcher.getInstance();
        RecordSearcher recordSearcher = RecordSearcher.getInstance();
        RequestManager requestManager = RequestManager.getInstance();
        
        ArrayList<Request> requestList = requestSearcher.searchAllByKeyword(aClient);
        ArrayList<Record> recordList = recordSearcher.searchAllByKeyword(aClient.getEmail());
        
        String ret = "[Payment]\n";
        // TODO: remove remaining request
        int cnt = 0;
        for(Request request : requestList) {
            allRequests[cnt++] = request;
            requestManager.removeRequest(request);
        }
        // TODO: compute amount
        double tot = 0.0;
        for(Record record : recordList) {
            if(record.getPaymentStatus()) {
                continue;
            }
            record.setPaymentStatus(true);
            Rentable rentable = record.getRentable();
            double price = rentable.getPrice();
            tot += price;
            ret += String.format(">%-5s\t$%.2f\n", rentable.getId(), price);
        }
        tot *= aClient.getDiscount();
        ret += String.format("\ndiscount: %.0f percent off\n", (1-aClient.getDiscount()) * 100);
        ret += String.format("total: $%.2f\n", tot);
        return ret;
    }
    
    @Override
    public String undo(){
        String ret = "[Undo]\n";
        RequestManager requestManager = RequestManager.getInstance();
        for(Request request : allRequests) {
            requestManager.newRequest(request);
            ret += String.format("> confirm request[%s] unused\n", request.getRentable().getId());
        }
        return ret;
    }
    
    @Override
    public String redo(){
        String ret = "[Redo]\n";
        RequestManager requestManager = RequestManager.getInstance();
        for(Request request : allRequests) {
            requestManager.removeRequest(request);
            ret += String.format("> confirm request[%s] unused\n", request.getRentable().getId());
        }
        return ret;
    }
}
