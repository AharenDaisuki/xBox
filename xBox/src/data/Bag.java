package data;

public class Bag extends Rentable{
    final String type = "B";

    public Bag(String bagID, RentableStatus status){
        super(bagID, status);
    }

    @Override
    public String getType(){
        return type;
    }
}
