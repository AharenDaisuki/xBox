package data;

public class Bag extends Rentable{

    public Bag(String i){
        super(i);
    }

    @Override
    public String getType(){
        return "Bag";
    }
}
