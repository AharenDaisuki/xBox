package test;

import data.*;
import ex.ExNoSufficientRentable;

import org.junit.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestRentableAllocator {
    Client student1 = new ClientStudent("zy@gmail.com","46464646","myPassword");
    
    @Test
    public void test_01() throws ExNoSufficientRentable {
        RentableAllocator ra = RentableAllocator.getInstance();
        RentableManager rm = RentableManager.getInstance();
        RentableStatus rs = new RentableStatusAvailable();
        rm.addNewRentable(new Box("0001",rs));
        
        try {
            ra.borrowRentable(student1, "BOX");
        } catch (ExNoSufficientRentable e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void test_02() {
        RentableAllocator ra = RentableAllocator.getInstance();
        RentableManager rm = RentableManager.getInstance();
        RentableStatus rs = new RentableStatusAvailable();

        try {
            int a=1;
            ra.borrowRentable(student1, "BAG");
        } catch (ExNoSufficientRentable e) {
            e.printStackTrace();
        }
    }
    
    
}
