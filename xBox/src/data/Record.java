package data;

import java.util.Date;

public class Record {
	private Client client;
	private Rentable rentable;
	private Date dueDate;
	private boolean isPaid;

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
	
	public void setPaymentStatus() { this.isPaid = true; }

	@Override
	public String toString(){
	    return String.format("[record]: %s %s by %s, due by %tF", rentable.getId(), (isPaid ? "paid" : "occupied"), client.getEmail(), dueDate);
	}
}