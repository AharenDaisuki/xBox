package data;

import java.util.ArrayList;

public class Request {
	private Client client;
	private ArrayList<Rentable> rentableList;
	private Target target;
	
	public Request() {
		rentableList=new ArrayList<Rentable>();
	}
	public Request(Client aClient,ArrayList<Rentable> aRentableList,Target aTarget)
	{
		client=aClient;
		rentableList=aRentableList;
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

	public ArrayList<Rentable> getRentableList()
	{
		return rentableList;
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
