package data;
public class RentableStatusAvailable implements RentableStatus{

    @Override
    public String toString(){
        return "Available";
    }

    @Override
    public String getStatus(){
        return "Available";
    }
}