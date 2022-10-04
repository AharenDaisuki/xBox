package data;

import java.util.ArrayList;

public class RequestAndUse implements Target{
	public Day DueDate;
	public Client client;
	public RequestAndUse(Day aDate,Client aClient)
	{
		DueDate=aDate;
		client=aClient;
	}
	public void changeRentableStatus(ArrayList<Rentable> rentableList)
	{
		for(Rentable rentable: rentableList)
			rentable.setOccupied(DueDate, client);
	}
	
	@Override
	public String toString()
	{
		return "RequestAndUse";
	}
}
