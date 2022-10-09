package data;

import java.util.ArrayList;

public class RequestSearcher {
    private static RequestSearcher searcher=new RequestSearcher();
	
	public static RequestSearcher getInstance()
	{
		return searcher;
	}

    public ArrayList<Request> searchByClient(Client aClient)
    {
        RequestStorer storer=RequestStorer.getInstance();
        ArrayList<Request> result=new ArrayList<>();
        for(Request request:storer.getList())
        {
            if(request.getClient().getEmail()==aClient.getEmail())
                result.add(request);
        }
        return result;
    }

    public Request searchByRentableID(String aID){
        // TODO: not found exception
        RequestStorer storer = RequestStorer.getInstance();
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
