package data;

public class RequestManager {
	private static RequestManager instance = new RequestManager();
	
	private RequestManager() {}
	
	public static RequestManager getInstance(){
		return instance;
	}
	
	public void newRequest(Client aClient,Rentable aRentable,Target aTarget){
		RequestStorer storer=RequestStorer.getInstance();
		aRentable.setStatus(new RentableStatusRequested(aClient));
		storer.addEntry(new Request()); // TODO: to be replaced by a parameter-specified constructor
	}
	
	public void removeRequest(Request request){
		RequestStorer storer=RequestStorer.getInstance();
		request.getTarget().changeRentableStatus(request.getRentable());
		storer.delEntry(request);
	}
}
