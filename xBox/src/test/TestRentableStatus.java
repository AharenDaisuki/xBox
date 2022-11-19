package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.Test;
import data.*;

public class TestRentableStatus {
    Rentable box1 = new Box("0001", new RentableStatusAvailable());
    Client student1 = new ClientStudent("yzhou@gmail.com","46464646","password");
    Date due = new Date();
    
    RentableStatus rs1 = new RentableStatusAvailable();
  
    RentableStatus rs2 = new RentableStatusOccupied(due,student1);
    
    RentableStatus rs3 = new RentableStatusPending(student1);
    
    RentableStatus rs4 = new RentableStatusRequested(student1);
    
    @Test
    public void test_01() {
        String str1 = rs1.toString();
        assertEquals("Available",str1);
    }

    @Test
    public void test_02() {
        String str1 = rs1.getStatus();
        assertEquals("Available",str1);
    }

    @Test
    public void test_03() {
        String str1 = rs1.toJSONString();
        assertEquals("{\"status\":\"Available\"}",str1);
    }
    
    @Test
    public void test_04() {
        String str1 = rs2.toString();
        assertEquals("Borrowed by yzhou@gmail.com on "+due.toString(),str1);
    }
    
    @Test
    public void test_05() {
        String str1 = rs2.getStatus();
        assertEquals("Occupied",str1);
    }
    
    @Test
    public void test_06() {
        String str1 = rs2.toJSONString();
        assertEquals("{\"status\":\"Occupied\",\"client\":{\"email\":\"yzhou@gmail.com\",\"phoneNo\":\"46464646\",\"password\":\"password\",\"type\":\"student\"},\"due\":"+due.getTime()+"}",str1);
    }
    
    @Test
    public void test_07() {
        String str1 = rs3.toString();
        assertEquals("held by yzhou@gmail.com",str1);
    }

    @Test
    public void test_08() {
        String str1 = rs3.getStatus();
        assertEquals("Pending",str1);
    }

    @Test
    public void test_09() {
        String str1 = rs3.toJSONString();
        assertEquals("{\"status\":\"Pending\",\"client\":{\"email\":\"yzhou@gmail.com\",\"phoneNo\":\"46464646\",\"password\":\"password\",\"type\":\"student\"}}",str1);
    }
    
    @Test
    public void test_10() {
        String str1 = rs4.toString();
        assertEquals("Requested by yzhou@gmail.com",str1);
    }

    @Test
    public void test_11() {
        String str1 = rs4.getStatus();
        assertEquals("Requested",str1);
    }

    @Test
    public void test_12() {
        String str1 = rs4.toJSONString();
        assertEquals("{\"status\":\"Requested\",\"client\":{\"email\":\"yzhou@gmail.com\",\"phoneNo\":\"46464646\",\"password\":\"password\",\"type\":\"student\"}}",str1);
    }
    

}
