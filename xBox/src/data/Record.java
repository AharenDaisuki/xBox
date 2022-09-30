package data;

public class Record {
	private Client client;
	private Rentable box;
	private Day due;
	private String id;

	public Record(){}
	public Record(Client c,Rentable b,Day d,String i)
	{
		client=c;
		box=b;
		due=d;
		id=i;
	}

	public void setClient(Client c) {client=c;}
	public Client getClient() {return client;}

	public void setRentable(Rentable b) {box=b;}
	public Rentable getRentable() {return box;}
	
	public void setDue(Day d) {due=d;}
	public Day getDue() {return due;}
	
	public void setID(String i) {id=i;}
	public String getID() {return id;}
}
