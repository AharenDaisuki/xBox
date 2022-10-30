package data;

public class Box extends Rentable{
    private static final String type = "BOX";
    
    public Box(String id_, RentableStatus status_){
        super(Box.type + id_, status_);
    }

    @Override
    public String getType(){
        return Box.type;
    }

    @Override
    public String toString() {
    	return String.format("%-15s%-15s", this.getId(), this.getStatusStr().toString());
    }

    @Override
    public double getPrice() {
        return 100.0;
    }
    
    public String toJSONString()
    {
    	String str=super.toJSONString();
    	return str.substring(0,str.length()-1)+",\"type\":\"BOX\"}";
    }
}
