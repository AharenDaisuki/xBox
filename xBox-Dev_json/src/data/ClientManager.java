package data;

import java.util.ArrayList;

public class ClientManager {
	private static ClientManager instance=new ClientManager();
	
	public static ClientManager getInstance()
	{
		return instance;
	}
	public void insert(Client aClient)
	{
		ClientStorer storer=ClientStorer.getInstance();
		ArrayList<Client> Clientlist=storer.getList();
		Clientlist.add(aClient);
	}
	public void delete(Client aClient)
	{
		ClientStorer storer=ClientStorer.getInstance();
		ArrayList<Client> Clientlist=storer.getList();
		Clientlist.remove(aClient);
	}
}