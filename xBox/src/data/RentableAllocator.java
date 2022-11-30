package data;

import java.util.ArrayList;
import java.util.HashMap;

import ex.ExNoSufficientRentable;

import debug.DebugConfig;

public class RentableAllocator {
	private static RentableAllocator allocator = new RentableAllocator();
	
	public static RentableAllocator getInstance(){
		return allocator;
	}
	
	public Rentable borrowRentable(Client aClient, String aType) throws ExNoSufficientRentable{	
		RentableStorer storer = RentableStorer.getInstance();
		System.out.println(storer.getManager());
		
		
		HashMap<String,ArrayList<Rentable>> list = storer.getManager();
		boolean isFound = false; // NotFoundexception later
		System.out.println(list.get(aType));
		
		if (list.containsKey(aType)) {
    		for(Rentable rentable : list.get(aType)){
    		    System.out.println(rentable.toString());
    			if(rentable.getStatusStr().equals(RentableStatusAvailable.statusName)){
    				isFound = true;
                    return rentable;
    			}
    		}
		}
		throw new ExNoSufficientRentable(String.format("[Error] No sufficient %s!", aType));
        // return null; // NotFoundexception later
	}
}
