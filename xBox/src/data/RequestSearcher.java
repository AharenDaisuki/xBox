package data;

import java.util.ArrayList;

public class RequestSearcher {
    private static RequestSearcher searcher = new RequestSearcher();
	private static RequestStorer storer = RequestStorer.getInstance();
	
	public static RequestSearcher getInstance(){
		return searcher;
	}

    public ArrayList<Request> searchByClient(Client aClient){
        ArrayList<Request> result = new ArrayList<>();
        for(Request request:storer.getList()){
            if(request.getClient() == aClient) {
                result.add(request);
            }
        }
        return result;
    }

    public Request searchByRentableID(String aID){
        // TODO: not found exception
        Request result = null;
        for(Request request : storer.getList()){
            if(request.getRentable().getId().equals(aID)){
                result = request;
                break;
            }
        }
        return result;
    }
}
