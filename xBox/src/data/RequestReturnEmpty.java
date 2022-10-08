package data;

public class RequestReturnEmpty implements Target{
	

	@Override
	public void changeRentableStatus(Rentable rentable) {
		rentable.setStatus(new RentableStatusAvailable());
	}
	
	public String toString(){
		return "Request and return empty rentable";
	}

}
