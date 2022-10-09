package data;

import utils.XBoxDate;

public class Request {
	private Client client;
	private Rentable rentable;
	private Target target;
	private String requestId;
	//private Day date;
	private XBoxDate date;
	
	public Request() {
	
	}
	
	public Request(Client aClient,Rentable aRentable,Target aTarget, String id, XBoxDate aDate)
	{
		client = aClient;
		rentable = aRentable;
		target = aTarget;
		requestId = id;
		date = aDate;
	}
	
	public Client getClient()
	{
		return client;
	}
	public void setClient(Client aClient)
	{
		client=aClient;
	}
	
	public Rentable getRentable()
	{
		return rentable;
	}
	public void setRentable(Rentable aRentable)
	{
		rentable=aRentable;
	}

	public Target getTarget()
	{
		return target;
	}
	public void setTarget(Target aTarget)
	{
		target=aTarget;
	}
	
	public String getId()
	{
		return requestId;
	}

	@Override
	public String toString()
	{
		return rentable.getId()+" is requested by "+client.getEmail()+" on "+date.toString();
	}
}
