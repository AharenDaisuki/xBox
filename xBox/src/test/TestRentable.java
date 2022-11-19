/**
 * 
 */
package test;


import data.*;
import org.junit.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

public class TestRentable {   
    Rentable bag1 = new Bag("0001", new RentableStatusAvailable());
    
    Client student1 = new ClientStudent("yzhou@gmail.com","46464646","password");
    Date due = new Date();
    Rentable Box1 = new Box("0001", new RentableStatusOccupied(due,student1));
    
    @Test
    public void test_01() {
       String type1 = bag1.getType();
       assertEquals("BAG",type1);
    }
    
    @Test 
    public void test_02() {
        String str1 = bag1.toString();
        assertEquals("BAG0001        Available      ",str1);
    }
    
    
    @Test 
    public void test_03() {
        double price1 = bag1.getPrice();
        assertEquals(80,price1);
    }
    
    @Test 
    public void test_04() {
        String str1 = bag1.toJSONString();
        assertEquals("{\"id\":\"0001\",\"status\":{\"status\":\"Available\"},\"type\":\"BAG\"}",str1);
    }
    
    @Test
    public void test_05() {
       String type1 = Box1.getType();
       assertEquals("BOX",type1);
    }
    
    @Test 
    public void test_06() {
        String str1 = Box1.toString();
        assertEquals("BOX0001        Occupied       ",str1);
    }
    
    
    @Test 
    public void test_07() {
        double price1 = Box1.getPrice();
        assertEquals(100,price1);
    }
    
    @Test 
    public void test_08() {
        String str1 = Box1.toJSONString();
        long dueStr = due.getTime();
        assertEquals("{\"id\":\"0001\",\"status\":{\"status\":\"Occupied\",\"client\":{\"email\":\"yzhou@gmail.com\",\"phoneNo\":\"46464646\",\"password\":\"password\",\"type\":\"student\"},\"due\":"+ dueStr +"},\"type\":\"BOX\"}",str1);
    }
    
    @Test 
    public void test_09() {
        String str1 = Box1.getStatus().getStatus();
        Box1.setStatus(new RentableStatusAvailable());
        assertEquals("Occupied",str1);
    }
    
    
    
    
}
