package data;

import java.util.ArrayList;

public class ClientSearcher {
	private static ClientSearcher searcher=new ClientSearcher();
	
	public static ClientSearcher getInstance(){
		return searcher;
	}


    public ArrayList<Client> searchAll() {
        ClientStorer storer = ClientStorer.getInstance();
        return storer.getList();
    }
   
    public Client searchByKeyword(String email) {
        ClientStorer storer = ClientStorer.getInstance();
        for(Client client : storer.getList()) {
            if(client.getEmail().equals(email)) {
                return client;
            }
        }
        return null;
    }
}