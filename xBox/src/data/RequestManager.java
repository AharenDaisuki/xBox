package data;

public class RequestManager {
	private static RequestManager instance=new RequestManager();
	
	public static RequestManager getInstance()
	{
		return instance;
	}
	public void newRequest(Client aClient,Rentable aRentable,Target aTarget)
	{
		RequestStorer storer=RequestStorer.getInstance();
		aRentable.setStatus(new RentableStatusRequested(aClient));
		storer.getList().add(new Request(aClient,aRentable,aTarget));
	}
	public void removeRequest(Request request)
	{
		RequestStorer storer=RequestStorer.getInstance();
		request.getTarget().changeRentableStatus(request.getRentable());
		storer.getList().remove(request);
	}
}
