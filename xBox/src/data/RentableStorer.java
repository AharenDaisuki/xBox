package data;

import java.util.HashMap;
import java.util.ArrayList;

public class RentableStorer implements XBoxStorer<Rentable>{
    private static RentableStorer instance;
    private HashMap<String, ArrayList<Rentable>> manager;

    private RentableStorer(){
        manager = new HashMap<>();
    }

    public static RentableStorer getInstance(){
        return instance;
    }

    public HashMap<String,ArrayList<Rentable>> getManager(){
        return manager;
    }

    @Override
    public void addEntry(Rentable entry) {
        String type = entry.getType();
        if(manager.get(type) == null) {
            manager.put(type, new ArrayList<>());
        }
        manager.get(entry.getType()).add(entry);
    }

    @Override
    public void delEntry(Rentable entry) {
        String type = entry.getType();
        if(manager.get(type) == null) {
            // TODO: exception
        }
        manager.get(entry.getType()).remove(entry);
    }
}
