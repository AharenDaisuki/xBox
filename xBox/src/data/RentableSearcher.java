/**
 * 
 */
package data;

import ex.ExEntryNotFound;

/**
 * @author lixiaoyang
 *
 */
public class RentableSearcher {
    private static RentableSearcher searcher = new RentableSearcher();
    
    public static RentableSearcher getInstance(){
        return searcher;
    }
    
    public Rentable searchByKeyword(String rentableId) throws ExEntryNotFound{
        RentableStorer storer = RentableStorer.getInstance();
        // TODO: the first three letter is type
        for(Rentable rentable : storer.getList(rentableId.substring(0, 3))) {
            if(rentable.getId().equals(rentableId)) {
                return rentable;
            }
        }
        throw new ExEntryNotFound(String.format("Item[%s] is not found", rentableId));
        // return null;
    }
}
