package data;

import java.util.ArrayList;

public class RecordStorer implements XBoxStorer<Record>{
	private ArrayList<Record> recordList;
	private static RecordStorer storer = new RecordStorer();
	
	private RecordStorer(){
		recordList=new ArrayList<>();
	}

	public static RecordStorer getInstance(){
		return storer;
	}
	
	public ArrayList<Record> getList(){
		return recordList;
	}

    @Override
    public void addEntry(Record entry) {
        recordList.add(entry);
    }

    @Override
    public void delEntry(Record entry) {
        recordList.remove(entry);
    }
}
