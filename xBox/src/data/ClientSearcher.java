package data;

import java.util.ArrayList;

public class ClientSearcher {
	private static ClientSearcher searcher=new ClientSearcher();
	
	public static ClientSearcher getInstance()
	{
		return searcher;
	}
	
	public Client searchByClientEmail(String aEmail)
	{
		ClientStorer storer=ClientStorer.getInstance();
		ArrayList<Client> Clientlist=storer.getList();
		for(Client r:Clientlist)
			if(r.getEmail().equals(aEmail))
				return r;
		return null;
	}
	
}