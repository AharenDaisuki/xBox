package data;

import java.util.ArrayList;

public class RequestSearcher {
    private static RequestSearcher searcher = new RequestSearcher();
	private static RequestStorer storer = RequestStorer.getInstance();
	
	public static RequestSearcher getInstance(){
		return searcher;
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

    public Request searchByKeyword(String aID){
        for(Request request : storer.getList()){
            if(request.getRentable().getId().equals(aID)){
                return request;
            }
        }
        return null;
    }
}
