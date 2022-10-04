package data;

public class Box extends Rentable{

    public Box(String aId){
        super(aId);
    }

    @Override
    public String getType(){
        return "Box";
    }

    @Override
    public String toString() {
    	return String.format("%-5s%-70s", this.getId(), this.getStatus().toString());
    }
}
