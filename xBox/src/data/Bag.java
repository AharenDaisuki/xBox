package data;

public class Bag extends Rentable{

    public Bag(String aId){
        super(aId);
    }

    @Override
    public String getType(){
        return "Bag";
    }
}
