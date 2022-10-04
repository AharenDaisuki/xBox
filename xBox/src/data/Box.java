package data;

public class Box extends Rentable{
    final String type = "S";

    public Box(String boxID, RentableStatus status){
        super(boxID, status);
    }
    
    public String getId() {
    	return type + super.getId();
    }
    
    @Override
    public String toString() {
    	return String.format("%-5s%-70s", this.getId(), this.getStatus().toString());
    }

    @Override
    public String getType(){
        return type;
    }
}
