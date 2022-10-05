package data;
public class RentableStatusAvailable implements RentableStatus{

    @Override
    public String toString(){
        return "This Rentable is Available";
    }

    @Override
    public String getStatus(){
        return "Available";
    }
}