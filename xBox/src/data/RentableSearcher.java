/**
 * 
 */
package data;

import ex.ExEntryNotFound;

import java.util.ArrayList;

/**
 * @author lixiaoyang
 *
 */
public class RentableSearcher {
    private static RentableSearcher searcher = new RentableSearcher();
    
    public static RentableSearcher getInstance(){
        return searcher;
    }
    
    public Rentable searchByKeyword(String rentableId) {
        RentableStorer storer = RentableStorer.getInstance();
        // TODO: the first three letter is type
        
        if (storer.getList(rentableId.substring(0, 3)) != null){
            for(Rentable rentable : storer.getList(rentableId.substring(0, 3))) {
                if(rentable.getId().equals(rentableId)) {
                    return rentable;
                }
            }
        }
        return null;
        // throw new ExEntryNotFound(String.format("Item[%s] is not found", rentableId));

    }
    
    public ArrayList<Rentable> searchAllByKeyword(String type){
        RentableStorer storer  = RentableStorer.getInstance();
        return storer.getList(type);
        
    }
}
