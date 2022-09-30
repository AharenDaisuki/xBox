package data;

import java.util.ArrayList;

public class RecordManager {
	private static RecordManager instance=new RecordManager();
	
	public static RecordManager getInstance()
	{
		return instance;
	}
	public void insert(Client client,Rentable box,Day due, String id)
	{
		RecordStorer storer=RecordStorer.getInstance();
		ArrayList<Record> recordlist=storer.getList();
		recordlist.add(new Record(client,box,due,id));
	}
	public void delete(Rentable box)
	{
		RecordStorer storer=RecordStorer.getInstance();
		ArrayList<Record> recordlist=storer.getList();
		RecordSearcher searcher=RecordSearcher.getInstance();
		Record result=searcher.searchByRentableID(box.getId());
		recordlist.remove(result);
	}
}
