package data;

import java.util.ArrayList;

public class ClientSearcher {
	private static ClientSearcher searcher=new ClientSearcher();
	
	public static ClientSearcher getInstance()
	{
		return searcher;
	}
	
	public ArrayList<Client> searchByClientEmail(String aEmail)
	{
		ClientStorer storer=ClientStorer.getInstance();
		ArrayList<Client> Clientlist=storer.getList(),result=new ArrayList<>();
		for(Client r:Clientlist)
			if(r.getEmail().equals(aEmail))
				result.add(r);
		return result;
	}
	
}