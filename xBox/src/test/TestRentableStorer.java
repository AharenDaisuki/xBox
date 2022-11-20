package test;

import data.*;
import org.junit.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestRentableStorer {
    RentableStorer rs = RentableStorer.getInstance();
    
    @Test
    public void test_01() {
        Rentable box1 = new Box("0001", new RentableStatusAvailable());
        rs.addEntry(box1);
        boolean res = rs.getManager().containsKey("BOX");
        assertEquals(true,res);
    }
    
    @Test
    public void test_02() {
        Rentable box1 = new Box("0002", new RentableStatusAvailable());
        rs.delEntry(box1);
        boolean res = rs.getManager().containsValue(box1);
        assertEquals(false,res);
    }
    
    @Test
    public void test_03() {
        
    }

}
