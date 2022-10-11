package data;

public class Bag extends Rentable{
    private static final String type = "BAG";
    
    public Bag(String id_, RentableStatus status_){
        super(Bag.type + id_, status_);
    }

    @Override
    public String getType(){
        return Bag.type;
    }
}
