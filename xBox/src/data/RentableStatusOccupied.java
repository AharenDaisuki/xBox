package data;


public class RentableStatusOccupied implements RentableStatus{
    private Day borrowedDay;
    private Client borrowedPerson;

    public RentableStatusOccupied(Day d, Client c){
        borrowedDay=d;
        borrowedPerson=c;
    }

    @Override
    public String toString() {        
        return "Borrowed by "+ borrowedPerson.getIdAndName() +" on "+ borrowedDay;
    }       

    public Client getOwner(){
        return borrowedPerson;
    }

    @Override
    public String getStatus(){
        return "Borrowed";
    }

    
}