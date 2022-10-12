package data;

public class RecordManager {
	private static RecordManager instance=new RecordManager();
	
	public static RecordManager getInstance()
	{
		return instance;
	}
	public void insert(Record record){
	    RecordStorer storer = RecordStorer.getInstance();
	    storer.addEntry(record);
	}
	
	public void delete(Record record){
	    RecordStorer storer=RecordStorer.getInstance();
		storer.delEntry(record);
	}
}