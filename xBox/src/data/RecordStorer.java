package data;

import java.util.ArrayList;

public class RecordStorer {
	private ArrayList<Record> recordList;
	private static RecordStorer storer=new RecordStorer();
	
	private RecordStorer()
	{
		recordList=new ArrayList<>();
	}

	public static RecordStorer getInstance()
	{
		return storer;
	}
	public ArrayList<Record> getList()
	{
		return recordList;
	}
}
