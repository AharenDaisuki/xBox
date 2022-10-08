package data;

import java.util.ArrayList;
import java.util.HashMap;

public class RentableManager {
    private static RentableManager instance=new RentableManager();
    
    public static RentableManager getInstance(){
        return instance;
    }

    public void addNewRentableType(Rentable aRentable){
        RentableStorer rs = RentableStorer.getInstance();
        HashMap<String,ArrayList<Rentable>> manager = rs.getManager();
        manager.put(aRentable.getType(), new ArrayList<>());
    }

    public void deleteRentableType(Rentable aRentable){
        RentableStorer rs = RentableStorer.getInstance();
        HashMap<String,ArrayList<Rentable>> manager = rs.getManager();
        manager.remove(aRentable.getType());
    }

    public void addNewRentable(Rentable aRentable)
    {
        RentableStorer rs = RentableStorer.getInstance();
        HashMap<String,ArrayList<Rentable>> manager = rs.getManager();
        manager.get(aRentable.getType()).add(aRentable);
    }
    
    public void deleteRentable(Rentable aRentable)
    {
        RentableStorer rs = RentableStorer.getInstance();
        HashMap<String,ArrayList<Rentable>> manager = rs.getManager();
        manager.get(aRentable.getType()).remove(aRentable);
    }
    /*
    public void lendOutRentable(Rentable aRentable){
        RentableStorer rs = RentableStorer.getInstance();
        HashMap<String,ArrayList<Rentable>> manager = rs.getManager();
        manager.get(aRentable.getType()).add(aRentable);
    }

    public void getBackRentable(Rentable aRentable){
        RentableStorer rs = RentableStorer.getInstance();
        HashMap<String,ArrayList<Rentable>> manager = rs.getManager();
        manager.get(aRentable.getType()).remove(aRentable);
    }
    */
}
