package data;

import java.util.ArrayList;

public class RequestManager {
	private static RequestManager instance=new RequestManager();
	
	public static RequestManager getInstance()
	{
		return instance;
	}
	public void newRequest(Client aClient,ArrayList<Rentable> aRentableList,Target aTarget)
	{
		for(Rentable rentable:aRentableList)
			rentable.setRequested(aClient);
		RequestStorer storer=RequestStorer.getInstance();
		storer.getList().add(new Request(aClient,aRentableList,aTarget));
	}
	public void removeRequest(Request request)
	{
		RequestStorer storer=RequestStorer.getInstance();
		storer.getList().remove(request);
	}
}
