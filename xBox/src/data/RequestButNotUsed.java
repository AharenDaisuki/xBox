package data;

import java.util.ArrayList;

public class RequestButNotUsed implements Target{
	public void changeRentableStatus(Rentable rentable)
	{
			rentable.setStatus(new RentableStatusAvailable());
	}
	public String toString()
	{
		return "RequestButNotUse";
	}
}
