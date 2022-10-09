package data;
public class RentableStatusAvailable implements RentableStatus{
	public static final String statusName = "Available";
	
	public RentableStatusAvailable() {}
	
    @Override
    public String toString(){
        return "[status]: Available";
    }

    @Override
    public String getStatus(){
        return "Available";
    }
}