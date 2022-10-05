package data;

public class Request {
	private Client client;
	private Rentable rentable;
	private Target target;
	
	public Request() {
	}
	public Request(Client aClient,Rentable aRentable,Target aTarget)
	{
		client=aClient;
		rentable=aRentable;
		target=aTarget;
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
}
