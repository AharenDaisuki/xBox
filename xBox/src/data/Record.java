package data;

public class Record {
	private Client client;
	private Rentable aRentable;
	private Day due;
	private String id;

	public Record(){}
	public Record(Client c,Rentable r,Day d,String Id)
	{
		client=c;
		aRentable=r;
		due=d;
		id=Id;
	}

	public void setClient(Client c) {client=c;}
	public Client getClient() {return client;}

	public void setRentable(Rentable r) {aRentable=r;}
	public Rentable getRentable() {return aRentable;}
	
	public void setDue(Day d) {due=d;}
	public Day getDue() {return due;}
	
	public void setID(String Id) {id=Id;}
	public String getID() {return id;}

	@Override
	public String toString()
	{
		return aRentable.getId()+" is occupied by "+client.getEmail()+". The due date is "+due.toString();
	}
}