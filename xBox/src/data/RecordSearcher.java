package data;

import java.util.ArrayList;

public class RecordSearcher {
	private static RecordSearcher searcher=new RecordSearcher();
	
	public static RecordSearcher getInstance(){
		return searcher;
	}
	
	public Record searchByRecordID(String aRecordID){
		RecordStorer storer=RecordStorer.getInstance();
		ArrayList<Record> recordlist=storer.getList();
		for(Record r:recordlist)
			if(r.getID().equals(aRecordID))
				return r;
		return null; // exception later
	}
	
	public ArrayList<Record> searchByClientName(String email){
		RecordStorer storer=RecordStorer.getInstance();
		ArrayList<Record> recordlist=storer.getList(),result=new ArrayList<>();
		for(Record r:recordlist)
			if(r.getClient().getEmail().equals(email)) {
			    result.add(r);
			}
		return result;
	}
	
	/*
	public ArrayList<Record> searchByClientSid(String str)
	{
		RecordStorer storer=RecordStorer.getInstance();
		ArrayList<Record> recordlist=storer.getList(),result=new ArrayList<>();
		for(Record r:recordlist)
			if(r.getClient().getId().equals(aID))
				result.add(r);
		return result;
	}*/
	
	public ArrayList<Record> searchByClientEmail(String aEmail)
	{
		RecordStorer storer=RecordStorer.getInstance();
		ArrayList<Record> recordlist=storer.getList(),result=new ArrayList<>();
		for(Record r:recordlist)
			if(r.getClient().getEmail().equals(aEmail))
				result.add(r);
		return result;
	}
	public Record searchByRentableID(String aRentableID)
	{
		RecordStorer storer=RecordStorer.getInstance();
		ArrayList<Record> recordlist=storer.getList();
		for(Record r:recordlist)
			if(r.getRentable().getId().equals(aRentableID))
				return r;
		return null;
	}
	public ArrayList<Record> searchByRentableType(String aRentableType)
	{
		RecordStorer storer=RecordStorer.getInstance();
		ArrayList<Record> recordlist=storer.getList(),result=new ArrayList<>();
		for(Record r:recordlist)
			if(r.getRentable().getType().equals(aRentableType))
				result.add(r);
		return result;
	}
	public ArrayList<Record> searchByDue(Day aDue)
	{
		RecordStorer storer=RecordStorer.getInstance();
		ArrayList<Record> recordlist=storer.getList(),result=new ArrayList<>();
		for(Record r:recordlist)
			if(r.getDue().equals(aDue))
				result.add(r);
		return result;
	}
}