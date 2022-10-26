package data;
public class RentableStatusAvailable implements RentableStatus{
	public static final String statusName = "Available";
	
	public RentableStatusAvailable() {}
	
    @Override
    public String toString(){
        return "Available";
    }

    @Override
    public String getStatus(){
        return "Available";
    }

    public String toJSONString()
    {
    	return "{\"status\":\"Available\"}";
    }
}