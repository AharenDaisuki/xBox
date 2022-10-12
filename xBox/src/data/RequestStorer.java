package data;

import java.util.ArrayList;

public class RequestStorer implements XBoxStorer<Request>{
    // data
    private ArrayList<Request> requestList;
    //private static int distributedId = 0;
    
    // singleton 
	private static RequestStorer storer = new RequestStorer();
	
	private RequestStorer(){
		requestList = new ArrayList<>();
	}

	public static RequestStorer getInstance(){
		return storer;
	}
	
	/*
	public int generateRequestId() {
	    return distributedId++;
	}*/
	
	// TODO: replace
	public ArrayList<Request> getList(){
		return requestList;
	}

    @Override
    public void addEntry(Request request_) {
        requestList.add(request_);
    }

    @Override
    public void delEntry(Request request_) {
        requestList.remove(request_);
    }
}
