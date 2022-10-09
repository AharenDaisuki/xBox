package data;

import utils.XBoxDate;

public class RequestAndUse implements Target{
	public XBoxDate dueDate;
	public Client client;
	
	public RequestAndUse(XBoxDate aDate,Client aClient){
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
	
	public XBoxDate getDueDate(){
		return this.dueDate;
	}
}
