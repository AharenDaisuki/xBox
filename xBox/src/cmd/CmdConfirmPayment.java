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
    private final Rentable[] allRentable = new Rentable[size];
    private final RentableStatus[] allStatus = new RentableStatus[size];
    private final RentableStatus[] allNewStatus = new RentableStatus[size];
    private int num = 0;
    
    public String execute(String[] cmdLine, Client aClient){
        /*
         * email
         *
         */
        RequestSearcher requestSearcher = RequestSearcher.getInstance();
        RecordSearcher recordSearcher = RecordSearcher.getInstance();
        RequestManager requestManager = RequestManager.getInstance();
        
        ArrayList<Request> requestList = requestSearcher.searchAllByKeyword(aClient.getEmail());
        ArrayList<Record> recordList = recordSearcher.searchAllByKeyword(aClient.getEmail());
        
        String ret = "[Payment]\n";
        // TODO: remove remaining request
        this.num = 0;
        for(Request request : requestList) {
            allRequests[num] = request;
            allRentable[num] = request.getRentable();
            allStatus[num] = allRentable[num].getStatus();
            requestManager.removeRequest(request); // remove request
            allNewStatus[num] = new RentableStatusAvailable(); // set available
            allRentable[num].setStatus(allNewStatus[num]);
            this.num++;
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
        for(int i = 0; i < this.num; ++i) {
            Request request = allRequests[i];
            requestManager.newRequest(request);
            allRentable[i].setStatus(allStatus[i]);
            ret += String.format("> confirm request[%s] unused\n", request.getRentable().getId());
        }
        return ret;
    }
    
    @Override
    public String redo(){
        String ret = "[Redo]\n";
        RequestManager requestManager = RequestManager.getInstance();
        for(int i = 0; i < this.num; ++i) {
            Request request = allRequests[i];
            requestManager.removeRequest(request);
            allRentable[i].setStatus(allNewStatus[i]);
            ret += String.format("> confirm request[%s] unused\n", request.getRentable().getId());
        }
        return ret;
    }
}
