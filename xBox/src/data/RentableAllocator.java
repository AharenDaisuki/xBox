package data;

import java.util.ArrayList;
import java.util.HashMap;

public class RentableAllocator {
	private static RentableAllocator allocator=new RentableAllocator();
	
	public static RentableAllocator getInstance()
	{
		return allocator;
	}
	public Rentable borrowRentable(Client aClient,String aType)
	{
		RentableStorer storer=RentableStorer.getInstance();
		HashMap<String,ArrayList<Rentable>> list=storer.getManager();
		boolean isFound=false; // NotFoundexception later
		for(Rentable r:list.get(aType))
		{
			if(r.getStatus().equals("Available"))
			{
				isFound=true;
                return r;
			}
		}
        return null; // NotFoundexception later
	}
}
