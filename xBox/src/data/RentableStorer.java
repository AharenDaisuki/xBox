package data;

import java.util.HashMap;
import java.util.ArrayList;

public class RentableStorer {
    private static RentableStorer instance;
    private HashMap<String,ArrayList<Rentable>> manager;

    private RentableStorer(){
        manager = new HashMap<>();
    }

    public static RentableStorer getInstance(){
        return instance;
    }

    public HashMap<String,ArrayList<Rentable>> getManager(){
        return manager;
    }
    
}
