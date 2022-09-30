package data;

public class Bag extends Rentable{
    final String type = "B";

    public Bag(String i, String n){
        super(i,n);
    }

    @Override
    public String getType(){
        return type;
    }
}
