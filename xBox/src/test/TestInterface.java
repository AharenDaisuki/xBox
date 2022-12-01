package test;


import ex.*;
import org.junit.Test;

import app.Main;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import io.*;
import java.io.IOException;
import xBox.AdminInterfaces;
import xBox.UserInterfaces;
import xBox.Xbox;
import cmd.CmdConfirmPayment;
import cmd.CmdConfirmReturn;
import cmd.CmdRequestRentable;
import cmd.Undoable;
import data.*;
import data.Record;

public class TestInterface {
    Xbox xbox;
    public TestInterface() {
        Xbox.totest();
        xbox = Xbox.getInstance();
        Database db = Database.getInstance();
        String[] files = {
          System.getProperty("user.dir") + "/src/datasrc/RentableStorer.json", // TODO: to be modified
          System.getProperty("user.dir") + "/src/datasrc/RecordStorer.json",
          System.getProperty("user.dir") + "/src/datasrc/ClientStorer.json",
          System.getProperty("user.dir") + "/src/datasrc/RequestStorer.json"
        };
        // initialization
        try {
            db.initialize(files);
        } catch (IOException ex) {
        }
    } 
   
    @Test
    public void Test_login(){
      UserInterfaces u = new UserInterfaces();
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
      System.out.print(page);
      assertEquals(String.format("Login Success <%s>","test" ),page);
      
      test.clickbtn1("admin@xbox.com.hk","20030109");
      page = xbox.get_data();
      assertEquals(String.format("Login Success <%s>","admin@xbox.com.hk" ),page);
      
    }
    @Test
    public void Test_login_2() {
        UserInterfaces u = new UserInterfaces();
        login test = new login(u,new AdminInterfaces());
        test.clickbtn1("1","1");
        String data = Xbox.get_error_message();
        assertEquals(String.format("[Error] No user <%s>, please register first!", "1"),data);
        test.clickbtn1("test","2");
        data = Xbox.get_error_message();
        assertEquals("[Error] The password is invalid!",data);
    }
    @Test
    public void Test_register() {      
        UserInterfaces u =new UserInterfaces();
        register test = new register(u);
        register.load(u);
        String page = null;
        page = xbox.get_page();
        assertEquals("register",page);
          
        test.clickbtn2();
        page = xbox.get_page();
        assertEquals("login_or_register",page);
        
        test.clickbtn1("1", "1", "1", "staff");
        page = xbox.get_data();
        assertEquals(String.format("Register Success <%s>", "1"),page);
        
        
    } 
    @Test
    public void Test_register_2() {

        UserInterfaces u = new UserInterfaces();
        register test = new register(u);
        test.clickbtn1(null, null, null, "student");
        String data  =Xbox.get_error_message();
        assertEquals("[Error] Please fill in all blank fields!",data);
        test.clickbtn1("test", "1", "1", "student");
        data  =Xbox.get_error_message();
        assertEquals(String.format("[Error] Email address <%s> has been registered!", "test"),data);
    }
    @Test
    public void Test_register_3() {      
        UserInterfaces u =new UserInterfaces();
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
        assertEquals(String.format("Register Success <%s>", "1"),page);
    }
    @Test
    public void Test_AmdinPage() throws ExEntryNotFound {
        AdminInterfaces a = new AdminInterfaces();
        AdminPage test = new AdminPage(a);
        AdminPage.load(a);
        String page = xbox.get_page();
        assertEquals("AdminPage",page);
        
        test.clickbtn0();
        page = xbox.get_page();
        assertEquals("login_or_register",page);
        test.clickbtn1();
        assertEquals("confirmPayment",xbox.get_page());
        confirmPayment cp= new confirmPayment(a);
        cp.clickbtn1("test");
        ClientSearcher clientSearcher = ClientSearcher.getInstance();
        Client client = clientSearcher.searchByKeyword("test");
        String[] cmdLine= {"test"};
        String ret = (new CmdConfirmPayment()).execute(cmdLine, client);
        assertEquals(ret,xbox.get_data());
        
        test.clickbtn2();
        assertEquals("confirmReturn",xbox.get_page());
        confirmReturn cr= new confirmReturn(a);
        cr.clickbtn1("test");
        ret = (new CmdConfirmReturn()).execute(cmdLine, client);
        assertEquals(ret,xbox.get_data());
        
        test.clickbtn3();
        assertEquals("SearchItem",xbox.get_page());
        SearchItem si= new SearchItem(a);
        si.clickbtn1("BAG1001");
        ret = "";
        RentableSearcher rentableSearcher = RentableSearcher.getInstance();
        Rentable rentable = rentableSearcher.searchByKeyword("BAG1001");
        ret += String.format("%-15s%-15s\n", "[ID]", "[STATUS]");
        ret += String.format("%s\n", rentable.toString());
        assertEquals(ret,xbox.get_data());
        test.clickbtn4();
        assertEquals("SearchClient",xbox.get_page());
        SearchClient sc= new SearchClient(a);
        sc.clickbtn1("admin@xbox.com.hk");
        ret = "";
        client = clientSearcher.searchByKeyword("admin@xbox.com.hk");
        ret += String.format("%-25s%-10s\n", "[EMAIL]", "[TEL]");
        ret += String.format("%s\n", client.toString());
        assertEquals(ret,xbox.get_data());
        test.clickbtn5();
        ret = "<Request Summary>:\n";
        ret += String.format("%-15s%-25s%-10s\n", "[ID]", "[EMAIL]", "[DATE]");
        RequestSearcher searcher1 = RequestSearcher.getInstance();
        for(Request request : searcher1.searchAll()) {
            ret += String.format("%s\n", request.toString());
        }
        assertEquals(ret,xbox.get_data());
        test.clickbtn6();
        ret = "<Record Summary>:\n";
        ret += String.format("%-15s%-15s%-25s%-10s\n", "[ID]", "[PAYMENT]", "[EMAIL]", "[DUE]");
        RecordSearcher searcher = RecordSearcher.getInstance();
        for(Record record : searcher.searchAll()) {
            ret += String.format("%s\n", record.toString());
        }
        assertEquals(ret,xbox.get_data());
        
    }
    @Test
    public void Test_AdminPage_2() throws ExEmptyVector {
        AdminInterfaces a = new AdminInterfaces();
        AdminPage test = new AdminPage(a);
        test.clickbtn7();
        String ret = ">> Undo the following operations?\n";
        ret += Undoable.undoCmd();
        assertEquals(ret,xbox.get_data());
        test.clickbtn8();
        ret = ">> Redo the following operations?\n";
        ret += Undoable.redoCmd();
        assertEquals(ret,xbox.get_data());
    }
    @Test
    public void Test_AdminPage_3() throws ExEmptyVector {
        AdminInterfaces a = new AdminInterfaces();
        AdminPage test = new AdminPage(a);
        test.clickbtn1();
        assertEquals("confirmPayment",xbox.get_page());
        confirmPayment cp= new confirmPayment(a);
        cp.clickbtn1("2");
        String data = Xbox.get_error_message();
        assertEquals(String.format("[Error] <%s> not found!", "2"),data);
        test.clickbtn2();
        assertEquals("confirmReturn",xbox.get_page());
        confirmReturn cr= new confirmReturn(a);
        cr.clickbtn1("2");
        data = Xbox.get_error_message();
        assertEquals(String.format("[Error] <%s> not found!", "2"),data);
        test.clickbtn3();
        assertEquals("SearchItem",xbox.get_page());
        SearchItem si= new SearchItem(a);
        si.clickbtn1("BAG0002");
        data = Xbox.get_error_message();
        assertEquals(String.format("[Error] %s not found!", "BAG0002"),data);
        test.clickbtn4();
        assertEquals("SearchClient",xbox.get_page());
        SearchClient sc= new SearchClient(a);
        sc.clickbtn1("2");
        data = Xbox.get_error_message();
        assertEquals(String.format("[Error] <%s> not found!", "2"),data);
    }
    @Test
    public void Test_UserPage() throws ExNoSufficientRentable, ExEntryNotFound, ExInvalidPassword {
       
        UserInterfaces u = new UserInterfaces();
        String[] cmdLine= {"test","1"};
        u.login(cmdLine);
        UserPage test =  new UserPage(u);
        UserPage.load(u);
        assertEquals("UserPage",xbox.get_page());
        
        test.clickbtn0();
        assertEquals("login_or_register",xbox.get_page());
        test.clickbtn1();
        assertEquals("requestBox",xbox.get_page());
        requestBox rb= new requestBox(u);
        rb.clickbtn1("BAG", "1", "1");
  
        test.clickbtn2();
        assertEquals("storeBox",xbox.get_page());
        storeBox sb= new storeBox(u);
        sb.clickbtn1("BAG1001");
        test.clickbtn3();
        assertEquals("returnBox",xbox.get_page());
        returnBox reb= new returnBox(u);
        reb.clickbtn1("BAG1001");

    }
    @Test
    public void Test_UserPage_2() throws ExEmptyVector {
        
        UserInterfaces u = new UserInterfaces();
        UserPage test =  new UserPage(u);
        test.clickbtn5();
        String ret = ">> Undo the following operations?\n";
        ret += Undoable.undoCmd();
        assertEquals(ret,xbox.get_data());
        test.clickbtn6();
        ret = ">> Redo the following operations?\n";
        ret += Undoable.redoCmd();
        assertEquals(ret,xbox.get_data());
    }
    @Test
    public void SystemTest() {
        Main main=new Main();
        main.main(null);
    }
    
}
