package test;

import data.*;
import org.junit.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestRentableManager {
    RentableManager rm = RentableManager.getInstance();
    
    @Test
    public void test_01() {
        RentableStatus rs = new RentableStatusAvailable();
        Rentable box1 = new Box("0001",rs);
        RentableStorer rst = RentableStorer.getInstance();
        
        rm.addNewRentable(box1);
        
        boolean res = false;
        for (Rentable r : rst.getList("BOX")) {
            if (r.equals(box1)) {res = true; break;}
        }
        
        assertEquals(true,res);
    }
    
    @Test
    public void test_02() {
        RentableStatus rs = new RentableStatusAvailable();
        Rentable box1 = new Box("0001",rs);
        RentableStorer rst = RentableStorer.getInstance();
        
        rm.deleteRentable(box1);
        
        boolean res = false;
        for (Rentable r : rst.getList("BOX")) {
            if (r.equals(box1)) {res = true; break;}
        }
        
        assertEquals(false,res);
    }

}
