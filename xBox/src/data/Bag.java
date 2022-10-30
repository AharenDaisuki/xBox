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
    
    @Override
    public String toString() {
        return String.format("%-15s%-15s", this.getId(), this.getStatusStr().toString());
    }

    @Override
    public double getPrice() {
        return 80.0;
    }
    
    public String toJSONString()
    {
    	String str=super.toJSONString();
    	return str.substring(0,str.length()-1)+",\"type\":\"BAG\"}";
    }
}
