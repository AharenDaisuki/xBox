package data;

public class Box extends Rentable{

    public Box(String aId){
        super(aId);
    }

    @Override
    public String getType(){
        return "Box";
    }
}
