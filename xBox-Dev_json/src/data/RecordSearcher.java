package data;

import java.util.ArrayList;
import java.util.Date;

import ex.ExEntryNotFound;

public class RecordSearcher{
	private static RecordSearcher searcher=new RecordSearcher();
	
	public static RecordSearcher getInstance(){
		return searcher;
	}
	// search by rentable id
	public Record searchByKeyword(String rentableId) throws ExEntryNotFound{
	    RecordStorer storer = RecordStorer.getInstance();
	    for(Record record : storer.getList()) {
	        if(record.getRentable().getId().equals(rentableId)) {
	            return record;
	        }
	    }
	    throw new ExEntryNotFound(String.format("Record[%s] is not found", rentableId));
	    // return null;
	}
	// search by rentable 
	public Record searchByKeyword(Rentable rentable) throws ExEntryNotFound{
	       RecordStorer storer = RecordStorer.getInstance();
	        for(Record record : storer.getList()) {
	            if(record.getRentable().equals(rentable)) {
	                return record;
	            }
	        }
	        throw new ExEntryNotFound(String.format("Record[%s] is not found", rentable.getId()));
	        // return null;
	}
	// search client email
	public ArrayList<Record> searchAllByKeyword(String email){
		RecordStorer storer = RecordStorer.getInstance();
		ArrayList<Record> result = new ArrayList<>();
		for(Record record : storer.getList()) {
		    if(record.getClient().getEmail().equals(email)) {
		        result.add(record);
		    }
		}
		return result;
	}
	// TODO: replace
	/*
	public ArrayList<Record> searchAllByRentableType(String rentableType){
		RecordStorer storer=RecordStorer.getInstance();
		ArrayList<Record> recordlist=storer.getList(),result=new ArrayList<>();
		for(Record r:recordlist)
			if(r.getRentable().getType().equals(aRentableType))
				result.add(r);
		return result;
	}*/
	// search date
	public ArrayList<Record> searchAllByKeyword(Date aDue){
		RecordStorer storer = RecordStorer.getInstance();
		ArrayList<Record> result = new ArrayList<>();
		for(Record record : storer.getList()) {
		    if(record.getDue().equals(aDue)) {
		        result.add(record);
		    }
		}
		return result;
	}
	
	public ArrayList<Record> searchAll(){
	    RecordStorer storer = RecordStorer.getInstance();
	    return storer.getList();
	}
}