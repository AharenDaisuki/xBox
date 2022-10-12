/**
 * 
 */
package data;

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
        for(Rentable rentable : storer.getList(rentableId.substring(0, 3))) {
            if(rentable.getId().equals(rentableId)) {
                return rentable;
            }
        }
        return null;
    }
}
