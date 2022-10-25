package data;

import utils.XBoxDate;
import java.util.Date;

public class RentableStatusOccupied implements RentableStatus{
    public static final String statusName = "Occupied";
    private Date borrowedDay; 
    private Client borrowedPerson;

    public RentableStatusOccupied(Date day_, Client client_){
        borrowedDay = day_;
        borrowedPerson = client_;
    }

    @Override
    public String toString() {        
        // TOOD: modify toString()
        return String.format("Borrowed by %s on %s", this.borrowedPerson.getEmail(), this.borrowedDay.toString());
    }       

    @Override
    public String getStatus(){
        return "Occupied";
    }
    
    public String toJSONString()
    {
    	return "{\"status\":\"Occupied\",\"client\":"+borrowedPerson.toJSONString()
    		+",\"due\":"+borrowedDay.getTime()+"}";
    }
}