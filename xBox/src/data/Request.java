package data;

import java.util.Date;

public class Request {
	private Client client;
	private Rentable rentable;
	// private Target target;
	// private int requestId;
	private Date date;
	
//	public Request() {}
	
	public Request(Client aClient, Rentable aRentable, Date aDate){
		client = aClient;
		rentable = aRentable;
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
	
	/*
	public Target getTarget()
	{
		return target;
	}
	public void setTarget(Target aTarget)
	{
		target=aTarget;
	}
	public int getId(){
		return requestId;
	}*/
	
	public Date getDue() {
	    return this.date;
	}

	@Override
	public String toString(){
	    return String.format("%-15s%-25s%tF", rentable.getId(), client.getEmail(), date);
	}
	
	public String toJSONString()
	{
		return "{\"client\":"+client.toJSONString()+",\"rentable\":"+rentable.toJSONString()
			+",\"dueDate\":"+date.getTime()+"}";
	}
}
