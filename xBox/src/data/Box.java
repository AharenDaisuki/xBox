package data;

public class Box extends Rentable{
    final String type = "A";

    public Box(String i, String n){
        super(i,n);
    }

    @Override
    public String getType(){
        return type;
    }
}
