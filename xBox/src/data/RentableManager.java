package data;

import java.util.ArrayList;
import java.util.HashMap;

public class RentableManager {
    private static RentableManager instance=new RentableManager();
    private HashMap<String,ArrayList<Rentable>> manager=new HashMap<>();
    
    public static RentableManager getInstance(){
        return instance;
    }

    public void addNewRentableType(Rentable r){
        manager.put(r.getType(), new ArrayList<>());
    }

    public void deleteRentableType(Rentable r){
        manager.remove(r.getType());
    }

    public void lendOutRentable(Rentable r){
        manager.get(r.getType()).add(r);
    }

    public void getBackRentable(Rentable r){
        manager.get(r.getType()).remove(r);
    }
}
