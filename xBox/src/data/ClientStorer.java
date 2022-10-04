package data;

import java.util.ArrayList;

public class ClientStorer{
	private ArrayList<Client> List;
    private static ClientStorer storer=new ClientStorer();
	
	private ClientStorer()
	{
		List=new ArrayList<>();
	}

	public static ClientStorer getInstance()
	{
		return storer;
	}
	public ArrayList<Client> getList()
	{
		return List;
	}
}
