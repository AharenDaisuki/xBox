package test;


import data.*;
import org.junit.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestRentableSearcher {
    RentableManager rm = RentableManager.getInstance();
    RentableSearcher rs = RentableSearcher.getInstance();
    RentableStatus rsta = new RentableStatusAvailable();
    Rentable box1 = new Box("0001", rsta);
    
    @Test
    public void test_01() {
        rm.addNewRentable(box1);
        rs.searchByKeyword("BOX0001");

        rs.searchAllByKeyword("BOX0001");
        
        rs.searchByKeyword("BAG0001");
    }

}
