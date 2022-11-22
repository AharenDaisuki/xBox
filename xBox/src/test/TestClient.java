package test;

import data.*;
import org.junit.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestClient {
    Client Staff1 = new ClientStaff("zsk@gmail.com","12345678","myPassword"); 
    Client student1 = new ClientStudent("zy@gmail.com","46464646","zyPassword");
    
    @Test
    public void test_01() {
       double dis = Staff1.getDiscount();
       assertEquals(0.8,dis);
    }
    
    @Test
    public void test_02() {
       int cnt1 = Staff1.getMaxBorrowedCount();
       assertEquals(20,cnt1);
    }
    
    @Test
    public void test_03() {
       String email1 = Staff1.getEmail();
       assertEquals("zsk@gmail.com",email1);
    }
    
    @Test
    public void test_04() {
       String pwd1 = Staff1.getPassword();
       assertEquals("myPassword",pwd1);
    }
    
    @Test
    public void test_05() {
       String phone1 = Staff1.getPhoneNo();
       assertEquals("12345678",phone1);
    }
    
    @Test
    public void test_06() {
       boolean verify = Staff1.verifyPassword("myPassword");
       assertEquals(true,verify);
    }
    
    @Test
    public void test_07() {
       boolean verify = Staff1.verifyPassword("notMyPassword");
       assertEquals(false,verify);
    }
    
    @Test
    public void test_08() {
       String str1 = Staff1.toString();
       assertEquals("zsk@gmail.com            12345678  ",str1);
    }
    
    @Test
    public void test_09() {
       String str1 = Staff1.toJSONString();
       assertEquals("{\"email\":\"zsk@gmail.com\",\"phoneNo\":\"12345678\",\"password\":\"myPassword\",\"type\":\"staff\"}",str1);
    }
    
    
    
    @Test
    public void test_10() {
       double dis = student1.getDiscount();
       assertEquals(0.9,dis);
    }
    
    @Test
    public void test_11() {
       int cnt1 = student1.getMaxBorrowedCount();
       assertEquals(10,cnt1);
    }
    
    @Test
    public void test_12() {
       String email1 = student1.getEmail();
       assertEquals("zy@gmail.com",email1);
    }
    
    @Test
    public void test_13() {
       String pwd1 = student1.getPassword();
       assertEquals("zyPassword",pwd1);
    }
    
    @Test
    public void test_14() {
       String phone1 = student1.getPhoneNo();
       assertEquals("46464646",phone1);
    }
    
    @Test
    public void test_15() {
       boolean verify = student1.verifyPassword("zyPassword");
       assertEquals(true,verify);
    }
    
    @Test
    public void test_16() {
       boolean verify = student1.verifyPassword("notMyPassword");
       assertEquals(false,verify);
    }
    
    @Test
    public void test_17() {
       String str1 = student1.toString();
       assertEquals("zy@gmail.com             46464646  ",str1);
    }
    
    @Test
    public void test_18() {
       String str1 = student1.toJSONString();
       assertEquals("{\"email\":\"zy@gmail.com\",\"phoneNo\":\"46464646\",\"password\":\"zyPassword\",\"type\":\"student\"}",str1);
    }
    
    @Test
    public void test_19() {
        Staff1.changeBorrowedCount(-2);
        int res = Staff1.getBorrowCount();
        assertEquals(18,res);
    }
    
    @Test
    public void test_20() {
        student1.changeBorrowedCount(-1);
        int res = student1.getBorrowCount();
        assertEquals(9,res);
    }
    
    

}
