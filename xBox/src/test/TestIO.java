package test;

import data.*;
import ex.*;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;


import io.*;
import xBox.AdminInterfaces;
import xBox.UserInterfaces;
import xBox.Xbox;


public class TestIO {
    Xbox xbox;
    public TestIO() {
        Xbox.totest();
        xbox = Xbox.getInstance();
    }
    
    @Test
    public void Test_login_or_register() {
        System.out.println("login_or_register");

        Xbox.totest();
        login_or_register test = new login_or_register();
        login_or_register.load();
        String page = null;
        page = xbox.get_page();
        assertEquals("login_or_register",page);
        
        test.clickbtn1();
        page = xbox.get_page();
        assertEquals("login",page);
        
        test.clickbtn2();
        page = xbox.get_page();
        assertEquals("register",page);
        
    }
    @Test
    public void Test_login(){
        class UserInterfaces_stub extends UserInterfaces{
            public String login(String[] a) {
                return "Success";
            }
        }
      UserInterfaces_stub u = new UserInterfaces_stub();
      login test = new login(u,new AdminInterfaces());
      login.load(u,new AdminInterfaces());
      String page = null;
      page = xbox.get_page();
      assertEquals("login",page);
      
      test.clickbtn2();
      page = xbox.get_page();
      assertEquals("login_or_register",page);
      
      test.clickbtn1("test","1");
      page = xbox.get_data();
      assertEquals("Success",page);
      
      test.clickbtn1("admin@xbox.com.hk","1");
      page = xbox.get_data();
      assertEquals("Success",page);
      
    }
    @Test
    public void Test_login_2() {
        class UserInterfaces_stub extends UserInterfaces{
            public String login(String[] a) throws ExEntryNotFound {
                throw new ExEntryNotFound("No Entry");
            }
        }
        UserInterfaces_stub u = new UserInterfaces_stub();
        login test = new login(u,new AdminInterfaces());
        test.clickbtn1("1","1");
        String data = Xbox.get_error_message();
        assertEquals("No Entry",data);
    }
    @Test
    public void Test_register() {
        class UserInterfaces_stub extends UserInterfaces{
            public String register(String[] a) {
                return "Success";
            }
        }
        UserInterfaces_stub u =new UserInterfaces_stub();
        register test = new register(u);
        register.load(u);
        String page = null;
        page = xbox.get_page();
        assertEquals("register",page);
          
        test.clickbtn2();
        page = xbox.get_page();
        assertEquals("login_or_register",page);
        
        test.clickbtn1("1", "1", "1", "student");
        page = xbox.get_data();
        assertEquals("Success",page);
        
        test.clickbtn1("1", "1", "1", "staff");
        page = xbox.get_data();
        assertEquals("Success",page);
    }
    @Test
    public void Test_register_2() {
        class UserInterfaces_stub extends UserInterfaces{
            public String register(String[] a) throws ExAccountExists {
                throw new ExAccountExists("Error");
            }
        }
        UserInterfaces_stub u = new UserInterfaces_stub();
        register test = new register(u);
        test.clickbtn1(null, null, null, "student");
        String data  =Xbox.get_error_message();
        assertEquals("Error",data);
    }
    @Test
    public void Test_AmdinPage() {
        class AdminInterfaces_stub extends AdminInterfaces{
            public String summaryAllRequests() {
                return "Success";
            }
            public String summaryAllRecords() {
                return "Success";
            }
            public String undo() {
                return "Success";
            }
            public String redo() {
                return "Success";
            }
        }
        AdminInterfaces_stub a = new AdminInterfaces_stub();
        AdminPage test = new AdminPage(a);
        AdminPage.load(a);
        String page = xbox.get_page();
        assertEquals("AdminPage",page);
        
        test.clickbtn0();
        page = xbox.get_page();
        assertEquals("login_or_register",page);
        test.clickbtn1();
        assertEquals("confirmPayment",xbox.get_page());
        test.clickbtn2();
        assertEquals("confirmReturn",xbox.get_page());
        test.clickbtn3();
        assertEquals("SearchItem",xbox.get_page());
        test.clickbtn4();
        assertEquals("SearchClient",xbox.get_page());
        test.clickbtn5();
        assertEquals("Success",xbox.get_data());
        test.clickbtn6();
        assertEquals("Success",xbox.get_data());
        test.clickbtn7();
        assertEquals("Success",xbox.get_data());
        test.clickbtn8();
        assertEquals("Success",xbox.get_data());
    }
    @Test
    public void Test_AdminPage_2() {
        class AdminInterfaces_stub extends AdminInterfaces{
            public String undo() throws ExEmptyVector{
                throw new ExEmptyVector("Error");
            }
            public String redo() throws ExEmptyVector{
                throw new ExEmptyVector("Error");
            }
        }
        AdminInterfaces_stub a = new AdminInterfaces_stub();
        AdminPage test = new AdminPage(a);
        test.clickbtn7();
        assertEquals("Error",Xbox.get_error_message());
        test.clickbtn8();
        assertEquals("Error",Xbox.get_error_message());
    }
    @Test
    public void Test_confirmPayment() {
        class AdminInterfaces_stub extends AdminInterfaces{
            public String confirmPayment(String[] a) {
                return "Success";
            }
        }
        AdminInterfaces_stub a = new AdminInterfaces_stub();
        confirmPayment test = new confirmPayment(a);
        confirmPayment.load(a);
        assertEquals("confirmPayment",xbox.get_page());
        
        test.clickbtn2();
        assertEquals("AdminPage",xbox.get_page());
        
        test.clickbtn1("1");
        assertEquals("Success",xbox.get_data());
    }
    @Test 
    public void Test_confirmPayment_2() {
        class AdminInterfaces_stub extends AdminInterfaces{
            public String confirmPayment(String[] a) throws ExEntryNotFound{
                throw new ExEntryNotFound("Error");
            }
        }
        AdminInterfaces_stub a = new AdminInterfaces_stub();
        confirmPayment test = new confirmPayment(a);
        test.clickbtn1("1");
        assertEquals("Error",Xbox.get_error_message());
    }
    
    @Test
    public void Test_confirmReturn() {
        class AdminInterfaces_stub extends AdminInterfaces{
            public String confirmReturn(String[] a) {
                return "Success";
            }
        }
        AdminInterfaces_stub a = new AdminInterfaces_stub();
        confirmReturn test = new confirmReturn(a);
        confirmReturn.load(a);
        assertEquals("confirmReturn",xbox.get_page());
        
        test.clickbtn2();
        assertEquals("AdminPage",xbox.get_page());
        
        test.clickbtn1("1");
        assertEquals("Success",xbox.get_data());
    }
    
    @Test
    public void Test_confirmReturn_2() {
        class AdminInterfaces_stub extends AdminInterfaces{
            public String confirmReturn(String[] a) throws ExEntryNotFound{
                throw new ExEntryNotFound("Error");
            }
        }
        AdminInterfaces_stub a = new AdminInterfaces_stub();
        confirmReturn test = new confirmReturn(a);
        test.clickbtn1("1");
        assertEquals("Error",Xbox.get_error_message());
    }
    
    @Test
    public void Test_SearchClient() {
        class AdminInterfaces_stub extends AdminInterfaces{
            public String summaryClient(String[] a) {
                return "Success";
            }
        }
        AdminInterfaces_stub a = new AdminInterfaces_stub();
        SearchClient test = new SearchClient(a);
        SearchClient.load(a);
        assertEquals("SearchClient",xbox.get_page());
        
        test.clickbtn2();
        assertEquals("AdminPage",xbox.get_page());
        
        test.clickbtn1("1");
        assertEquals("Success",xbox.get_data());
    }
    @Test 
    public void Test_SearchClient_2() {
        class AdminInterfaces_stub extends AdminInterfaces{
            public String summaryClient(String[] a) throws ExEntryNotFound{
                throw new ExEntryNotFound("Error");
            }
        }
        AdminInterfaces_stub a = new AdminInterfaces_stub();
        SearchClient test = new SearchClient(a);
        test.clickbtn1("1");
        assertEquals("Error",Xbox.get_error_message());
    }
    @Test
    public void Test_SearchItem() {
        class AdminInterfaces_stub extends AdminInterfaces{
            public String summaryItem(String[] a) {
                return "Success";
            }
        }
        AdminInterfaces_stub a = new AdminInterfaces_stub();
        SearchItem test = new SearchItem(a);
        SearchItem.load(a);
        assertEquals("SearchItem",xbox.get_page());
        
        test.clickbtn2();
        assertEquals("AdminPage",xbox.get_page());
        
        test.clickbtn1("1");
        assertEquals("Success",xbox.get_data());
    }
    @Test 
    public void Test_SearchItem_2() {
        class AdminInterfaces_stub extends AdminInterfaces{
            public String summaryItem(String[] a) throws ExEntryNotFound{
                throw new ExEntryNotFound("Error");
            }
        }
        AdminInterfaces_stub a = new AdminInterfaces_stub();
        SearchItem test = new SearchItem(a);
        test.clickbtn1("1");
        assertEquals("Error",Xbox.get_error_message());
    }
    @Test
    public void Test_UserPage() {
        class UserInterfaces_stub extends UserInterfaces{
            public String summary(String[] a) {
                return "Success";
            }
            public String undo() {
                return "Success";
            }
            public String redo() {
                return "Success";
            }
        }
        UserInterfaces_stub u = new UserInterfaces_stub();
        UserPage test =  new UserPage(u);
        UserPage.load(u);
        assertEquals("UserPage",xbox.get_page());
        
        test.clickbtn0();
        assertEquals("login_or_register",xbox.get_page());
        test.clickbtn1();
        assertEquals("requestBox",xbox.get_page());
        test.clickbtn2();
        assertEquals("storeBox",xbox.get_page());
        test.clickbtn3();
        assertEquals("returnBox",xbox.get_page());
        test.clickbtn4();
        assertEquals("Success",xbox.get_data());
        test.clickbtn5();
        assertEquals("Success",xbox.get_data());
        test.clickbtn6();
        assertEquals("Success",xbox.get_data());

    }
    @Test
    public void Test_UserPage_2() {
        class UserInterfaces_stub extends UserInterfaces{
            public String undo() throws ExEmptyVector {
                throw new ExEmptyVector("Error");
             }
            public String redo() throws ExEmptyVector {
                throw new ExEmptyVector("Error");
            }
        }
        UserInterfaces_stub u = new UserInterfaces_stub();
        UserPage test =  new UserPage(u);
        test.clickbtn5();
        assertEquals("Error",Xbox.get_error_message());
        test.clickbtn6();
        assertEquals("Error",Xbox.get_error_message());
    }
    @Test
    public void Test_requestBox() {
        class UserInterfaces_stub extends UserInterfaces{
            public String request(String[] a) {
                return "Success";
            }
        }        
        UserInterfaces_stub u = new UserInterfaces_stub();
        requestBox test =  new requestBox(u);
        requestBox.load(u);
        assertEquals("requestBox",xbox.get_page());
        test.clickbtn2();
        assertEquals("UserPage",xbox.get_page());
        test.clickbtn1("BOX", "1", "1");
        assertEquals("Success",xbox.get_data());
        test.clickbtn1("Bag", "1", "1");
        assertEquals("Success",xbox.get_data());
    }
    @Test
    public void Test_requestBox_2() {
        class UserInterfaces_stub extends UserInterfaces{
            public String request(String[] a) throws ExNoSufficientRentable{
                throw new ExNoSufficientRentable("Error");
            }
        }
        UserInterfaces_stub u = new UserInterfaces_stub();
        requestBox test =  new requestBox(u);
        test.clickbtn1("Bag", "1", "1");
        assertEquals("Error",Xbox.get_error_message());
    }
    @Test
    public void Test_returnBox() {
        class UserInterfaces_stub extends UserInterfaces{
            public String unload(String[] a) {
                return "Success";
            }
        }        
        UserInterfaces_stub u = new UserInterfaces_stub();
        returnBox test =  new returnBox(u);
        returnBox.load(u);
        assertEquals("returnBox",xbox.get_page());
        test.clickbtn2();
        assertEquals("UserPage",xbox.get_page());
        test.clickbtn1("1");
        assertEquals("Success",xbox.get_data());
    }
    @Test
    public void Test_returnBox_2() {
        class UserInterfaces_stub extends UserInterfaces{
            public String unload(String[] a) throws ExEntryNotFound{
                throw new ExEntryNotFound("Error");
            }
        }
        UserInterfaces_stub u = new UserInterfaces_stub();
        returnBox test =  new returnBox(u);
        test.clickbtn1("1");
        assertEquals("Error",Xbox.get_error_message());
    }
    @Test
    public void Test_storeBox() {
        class UserInterfaces_stub extends UserInterfaces{
            public String store(String[] a) {
                return "Success";
            }
        }        
        UserInterfaces_stub u = new UserInterfaces_stub();
        storeBox test =  new storeBox(u);
        storeBox.load(u);
        assertEquals("storeBox",xbox.get_page());
        test.clickbtn2();
        assertEquals("UserPage",xbox.get_page());
        test.clickbtn1("1");
        assertEquals("Success",xbox.get_data());
    }
    @Test
    public void Test_storeBox_2() {
        class UserInterfaces_stub extends UserInterfaces{
            public String store(String[] a) throws ExEntryNotFound{
                throw new ExEntryNotFound("Error");
            }
        }
        UserInterfaces_stub u = new UserInterfaces_stub();
        storeBox test =  new storeBox(u);
        test.clickbtn1("1");
        assertEquals("Error",Xbox.get_error_message());
    }
//    @Test
//    public void Test_login() {
//        System.out.println("login");
//
//        Xbox.totest();
//
//        String[] registerParams1 = {"admin@xbox.com.hk","1111111111", "whenisaylove", "staff"};
//        String[] registerParams2 = {"dongjiajie@gmail.com", "1111111111", "whenisaylove", "staff"}; // valid
//        UserInterfaces system = UserInterfaces.getInstance();
//        try {
//            system.register(registerParams1);
//            system.register(registerParams2);
//        } catch (Exception e) {
//            
//        }
//        login test = new login();
//        login.load();
//        String page = null;
//        page = xbox.get_page();
//        assertEquals("login",page);
//        
//        test.clickbtn2();
//        page = xbox.get_page();
//        assertEquals("login_or_register",page);
//        
//        test.clickbtn1("dongjiajie@gmail.com", "whenisaylove");
//        test.clickbtn1("admin@xbox.com.hk", "whenisaylove");
//        test.clickbtn1("admin@xbox.com.hk", "whenisaylve");
//
//    }
//    
//    @Test
//    public void Test_register() {
//        System.out.println("register");
//
//        Xbox.totest();
//
//        register test = new register();
//        register.load();
//        String page = null;
//        page = xbox.get_page();
//        assertEquals("register",page);
//        
//        test.clickbtn2();
//        page = xbox.get_page();
//        assertEquals("login_or_register",page);
//        
//        test.clickbtn1("dongjiajiedc@", "9333333", "whenisaylove", "student");        
//        test.clickbtn1("dongjiajie@", "9333333", "whenisayove", "staff");
//        test.clickbtn1("dongjiajie@", "9", "whenisayove", "staff");
//
//        
//        
//    }
//    @Test
//    public void Test_admin_page() {
//        System.out.println("admin page");
//
//        Xbox.totest();
//        AdminPage test = new AdminPage();
//        AdminPage.load();
//        String page = null;
//        page = xbox.get_page();
//        assertEquals("AdminPage",page);
//        
//        test.clickbtn0();
//        page = xbox.get_page();
//        assertEquals("login_or_register",page);
//
//        
//        test.clickbtn1();
//        page = xbox.get_page();
//        assertEquals("confirmPayment",page);
//
//        
//        test.clickbtn2();
//        page = xbox.get_page();
//        assertEquals("confirmReturn",page);    
//
//        
//        test.clickbtn3();
//        page = xbox.get_page();
//        assertEquals("SearchItem",page);  
//
//        
//        test.clickbtn4();
//        page = xbox.get_page();
//        assertEquals("SearchClient",page);        
//        
//        test.clickbtn5();
//        test.clickbtn6();
//
//        
//    }
//    @Test
//    public void Test_confirmPayment() {
//        System.out.println("confirmpayment");
//
//        Xbox.totest();
//        confirmPayment test = new confirmPayment();
//        confirmPayment.load();
//        String page = null;
//        page = xbox.get_page();
//        assertEquals("confirmPayment",page);
//        
//        test.clickbtn2();
//        page = xbox.get_page();
//        assertEquals("AdminPage",page);
//        
//        String[] registerParams1 = {"djj@gmail.com", "12345678", "password", "student"}; // valid
//        UserInterfaces system = UserInterfaces.getInstance();
//        try {
//            System.out.println(system.register(registerParams1));
//
//        } catch (Exception e) {
//            
//        }
//        test.clickbtn1("djj@gmail.com");
//        test.clickbtn1("error");
//    }
//
//    @Test
//    public void Test_confirmReturn() {
////      System.out.println("confirmReturn");
//        confirmReturn test = new confirmReturn();
//        String page =null;
//        confirmReturn.load();
//        page = xbox.get_page();
//        assertEquals("confirmReturn",page);
//        
//        String[] registerParams1 = {"djj", "12345678", "password", "student"}; // valid
//        UserInterfaces system = UserInterfaces.getInstance();
//        try {
//            System.out.println(system.register(registerParams1));
//
//        } catch (Exception e) {
//            
//        }
//        test.clickbtn1("djj");
//        test.clickbtn1("error");
//        
//        test.clickbtn2();
//        page = xbox.get_page();
//        assertEquals("AdminPage",page);
//
//    }
//    
//    @Test
//    public void Test_SearchItem() {
//        SearchItem test = new SearchItem();
//        String page = null;
//        SearchItem.load();
//        page = xbox.get_page();
//        assertEquals("SearchItem",page);
//        
//        test.clickbtn2();
//        page = xbox.get_page();
//        assertEquals("AdminPage",page);
//        
//        test.clickbtn1("error");
//        Box b = new Box("1",new RentableStatusAvailable());
//        
//        RentableStorer a = RentableStorer.getInstance();
//        a.addEntry(b);
//        test.clickbtn1("1");
//       
//    }
//    
}
