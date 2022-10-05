package data;

import java.util.ArrayList;

public class RequestButNotUsed implements Target{
	public void changeRentableStatus(ArrayList<Rentable> rentableList)
	{
		for(Rentable rentable: rentableList)
			rentable.setAvailale();
	}
	public String toString()
	{
		return "RequestButNotUse";
	}
}
