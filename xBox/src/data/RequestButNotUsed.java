package data;

import java.util.ArrayList;
import utils.XBoxDate;

public class RequestButNotUsed implements Target{
	
    public void changeRentableStatus(Rentable rentable){
			rentable.setStatus(new RentableStatusAvailable());
	}
	
	public String toString(){
		return "RequestButNotUse";
	}
	
	public XBoxDate getDueDate(){
		return null;
	}
}
