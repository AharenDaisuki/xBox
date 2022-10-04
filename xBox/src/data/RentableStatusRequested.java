package data;

public class RentableStatusRequested implements RentableStatus{
	Client client;
	public RentableStatusRequested(Client aClient)
	{
        client=aClient;
    }
	public String toString()
	{
		return "Requested By "+client.getId();
	}
	public String getStatus()
	{
		return "Requested";
	}
}
