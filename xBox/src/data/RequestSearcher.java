package data;

import java.util.ArrayList;

import ex.ExEntryNotFound;

public class RequestSearcher {
    private static RequestSearcher searcher = new RequestSearcher();
	private static RequestStorer storer = RequestStorer.getInstance();
	
	public static RequestSearcher getInstance(){
		return searcher;
	}
	
	public ArrayList<Request> searchAll(){
	    return storer.getList();
	}

    public ArrayList<Request> searchAllByKeyword(Client aClient){
        ArrayList<Request> result = new ArrayList<>();
        for(Request request:storer.getList()){
            if(request.getClient() == aClient) {
                result.add(request);
            }
        }
        return result;
    }

    public Request searchByKeyword(String aID) {
        for(Request request : storer.getList()){
            if(request.getRentable().getId().equals(aID)){
                return request;
            }
        }
        //throw new ExEntryNotFound(String.format("Request[%s] is not found", aID));
        return null;
    }
}
