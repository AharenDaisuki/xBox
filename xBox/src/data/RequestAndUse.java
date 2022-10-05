package data;

public class RequestAndUse implements Target{
	public Day dueDate;
	public Client client;
	public RequestAndUse(Day aDate,Client aClient)
	{
		dueDate=aDate;
		client=aClient;
	}
	public void changeRentableStatus(Rentable rentable)
	{
		rentable.setStatus(new RentableStatusOccupied(dueDate,client));
	}
	
	@Override
	public String toString()
	{
		return "RequestAndUse";
	}
}
