package data;

public class Box extends Rentable{

    public Box(String i){
        super(i);
    }

    @Override
    public String getType(){
        return "Box";
    }
}
