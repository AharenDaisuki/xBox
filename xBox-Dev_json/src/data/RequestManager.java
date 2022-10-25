package data;

import utils.XBoxDate;

public class RequestManager {
	private static RequestManager instance = new RequestManager();
	private static RequestStorer storer = RequestStorer.getInstance();
	
	private RequestManager() {}
	
	public static RequestManager getInstance(){
		return instance;
	}
	
	// add entry
	public void newRequest(Request request) {
	    storer.addEntry(request);
	}
	
	// delete entry
	public void removeRequest(Request request){
		storer.delEntry(request);
	}
}
