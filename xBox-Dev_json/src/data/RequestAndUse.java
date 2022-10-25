package data;

import utils.XBoxDate;
import java.util.Date;

public class RequestAndUse implements Target{
	public Date dueDate;
	public Client client;
	
	public RequestAndUse(Date aDate,Client aClient){
		dueDate = aDate;
		client = aClient;
	}
	
	public void changeRentableStatus(Rentable rentable){
		rentable.setStatus(new RentableStatusOccupied(dueDate,client));
	}
	
	@Override
	public String toString(){
		return "RequestAndUse";
	}

    @Override
    public XBoxDate getDueDate() {
        // TODO Auto-generated method stub
        return null;
    }
	
	// public Date getDueDate(){
	//	return this.dueDate;
	//}
}
