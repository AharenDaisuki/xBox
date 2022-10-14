package data;

import java.util.ArrayList;
import java.util.HashMap;

import ex.ExNoSufficientRentable;

public class RentableAllocator {
	private static RentableAllocator allocator = new RentableAllocator();
	
	public static RentableAllocator getInstance(){
		return allocator;
	}
	
	public Rentable borrowRentable(Client aClient, String aType) throws ExNoSufficientRentable{	
		RentableStorer storer = RentableStorer.getInstance();
		HashMap<String,ArrayList<Rentable>> list = storer.getManager();
		boolean isFound = false; // NotFoundexception later
		for(Rentable rentable : list.get(aType)){
			if(rentable.getStatusStr().equals(RentableStatusAvailable.statusName)){
				isFound = true;
                return rentable;
			}
		}
		throw new ExNoSufficientRentable();
        // return null; // NotFoundexception later
	}
}
