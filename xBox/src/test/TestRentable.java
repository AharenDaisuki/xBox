/**
 * 
 */
package test;

import data.*;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author lixiaoyang
 * Description: test class for rentable
 */
public class TestRentable {
    RentableManager rentableManager = RentableManager.getInstance();
    RentableAllocator rentableAllocator = RentableAllocator.getInstance();
    @Test
    public void TestAllocator(){
        rentableManager.addNewRentable(new Box("0001", new RentableStatusAvailable()));
        Client user = new ClientStudent("AharenDaisuki@gmail.com", "12345678", "");
        Rentable rentable = rentableAllocator.borrowRentable(user, "BOX");
        assertEquals("BOX0001", rentable.getId());
        assertEquals(RentableStatusAvailable.statusName, rentable.getStatusStr());
    }
}
