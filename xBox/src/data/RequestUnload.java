package data;

public class RequestUnload implements Target{
	private Client client;
	
	public RequestUnload(Client aClient){
		client=aClient;
	}
	
	@Override
	public void changeRentableStatus(Rentable rentable) {
		rentable.setStatus(new RentableStatusRequested(client));
	}
	
	public String toString(){
		return "Request and unload";
	}

	@Override
	public Day getDueDate() {
		// TODO Auto-generated method stub
		return null;
	}

}
