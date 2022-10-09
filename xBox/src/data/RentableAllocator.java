package data;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import utils.XBoxDate;

public class RentableAllocator {
	private static RentableAllocator allocator=new RentableAllocator();
	
	public static RentableAllocator getInstance(){
		return allocator;
	}
	
	public Rentable borrowRentable(Client aClient, String aType, XBoxDate date){	
		RentableStorer storer=RentableStorer.getInstance();
		HashMap<String,ArrayList<Rentable>> list=storer.getManager();
		boolean isFound = false; // NotFoundexception later
		for(Rentable rentable : list.get(aType)){
			if(rentable.getStatus().equals(RentableStatusAvailable.statusName)){
				isFound=true;
				rentable.setStatus(new RentableStatusOccupied(date, aClient));
                return rentable;
			}
		}
        return null; // NotFoundexception later
	}
}
