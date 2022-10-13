package data;

public class RentableStatusRequested implements RentableStatus{
    public static final String statusName = "Requested";
	private Client client;
	
	public RentableStatusRequested(Client aClient){
        client = aClient;
    }
	
	public String toString(){
	    return String.format("Requested by %s", this.client.getEmail());
		//return "Requested By "+client.getEmail();
	}
	
	public String getStatus(){
		return "Requested";
	}
}
