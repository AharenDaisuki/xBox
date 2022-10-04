package data;

import java.util.ArrayList;

public class RecordSearcher {
	private static RecordSearcher searcher=new RecordSearcher();
	
	public static RecordSearcher getInstance()
	{
		return searcher;
	}
	public Record searchByRecordID(String str)
	{
		RecordStorer storer=RecordStorer.getInstance();
		ArrayList<Record> recordlist=storer.getList();
		for(Record r:recordlist)
			if(r.getID().equals(str))
				return r;
		return null; // exception later
	}
	public ArrayList<Record> searchByClientName(String str)
	{
		RecordStorer storer=RecordStorer.getInstance();
		ArrayList<Record> recordlist=storer.getList(),result=new ArrayList<>();
		for(Record r:recordlist)
			if(r.getClient().getName().equals(str))
				result.add(r);
		return result;
	}
	
	/*
	public ArrayList<Record> searchByClientSid(String str)
	{
		RecordStorer storer=RecordStorer.getInstance();
		ArrayList<Record> recordlist=storer.getList(),result=new ArrayList<>();
		for(Record r:recordlist)
			if(r.getClient().getId().equals(str))
				result.add(r);
		return result;
	}*/
	
	public ArrayList<Record> searchByClientEmail(String str)
	{
		RecordStorer storer=RecordStorer.getInstance();
		ArrayList<Record> recordlist=storer.getList(),result=new ArrayList<>();
		for(Record r:recordlist)
			if(r.getClient().getEmail().equals(str))
				result.add(r);
		return result;
	}
	public Record searchByRentableID(String str)
	{
		RecordStorer storer=RecordStorer.getInstance();
		ArrayList<Record> recordlist=storer.getList();
		for(Record r:recordlist)
			if(r.getRentable().getId().equals(str))
				return r;
		return null;
	}
	public ArrayList<Record> searchByRentableType(String str)
	{
		RecordStorer storer=RecordStorer.getInstance();
		ArrayList<Record> recordlist=storer.getList(),result=new ArrayList<>();
		for(Record r:recordlist)
			if(r.getRentable().getType().equals(str))
				result.add(r);
		return result;
	}
	public ArrayList<Record> searchByDue(Day d)
	{
		RecordStorer storer=RecordStorer.getInstance();
		ArrayList<Record> recordlist=storer.getList(),result=new ArrayList<>();
		for(Record r:recordlist)
			if(r.getDue().equals(d))
				result.add(r);
		return result;
	}
}
