package data;

import utils.XBoxDate;

public class RentableStatusOccupied implements RentableStatus{
    public static final String statusName = "Occupied";
    private XBoxDate borrowedDay; 
    private Client borrowedPerson;

    public RentableStatusOccupied(XBoxDate day_, Client client_){
        borrowedDay = day_;
        borrowedPerson = client_;
    }

    @Override
    public String toString() {        
        // TOOD: modify toString()
        return String.format("[status]: Borrowed by %s on %s", this.borrowedPerson.getEmail(), this.borrowedDay.toString());
    }       

    @Override
    public String getStatus(){
        return "Occupied";
    }
    
    /*
    public Client getOwner(){
        return borrowedPerson;
    }*/
}