package data;

import java.util.Date;

public class Record {
	private Client client;
	private Rentable rentable;
	private Date dueDate;
	// private String id;

	public Record(){}
	
	public Record(Client client_, Rentable rentable_, Date due_){
		client = client_;
		rentable = rentable_;
		dueDate = due_;
	}

	public void setClient(Client c) { client = c; }
	
	public Client getClient() { return this.client; }

	public void setRentable(Rentable r) { rentable = r; }
	
	public Rentable getRentable() { return this.rentable; }
	
	public void setDue(Date d) { dueDate = d; }
	
	public Date getDue() {return this.dueDate; }
	
	//public void setID(String Id) {id=Id;}
	
	//public String getID() {return id;}

	@Override
	public String toString(){
	    return String.format("[record]: %s occupied by %s, due by %tF", rentable.getId(), client.getEmail(), dueDate);
	}
}