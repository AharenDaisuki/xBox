/**
 * 
 */
package test;

/**
 * @author lixiaoyang
 *
 */

import org.junit.Test;

import cmd.CmdStoreRentable;
import cmd.Command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import data.Box;
import data.Client;
import data.ClientStaff;
import data.Rentable;
import data.RentableStatusAvailable;
import data.Request;
import data.RequestManager;
import ex.ExEntryNotFound;
import ex.ExNoSufficientRentable;
import utils.XBoxDate;

import java.util.Date;


public class TestCmdStoreRentable {
    private RequestManager requestManager = RequestManager.getInstance();
    private XBoxDate systemDate = XBoxDate.getInstance();
    
    // n<= 0
    @Test
    public void test_01() {
        Client client = new ClientStaff("xyli45-c@my.cityu.edu.hk", "12345678", "11111111");
        //Rentable rentable = new Box("0001", new RentableStatusAvailable());
        //requestManager.newRequest(new Request(client, rentable, new Date()));
        
        Command storeRentable = new CmdStoreRentable();
        String log = null;
        String[] cmdLine = {};
        try {
            log = storeRentable.execute(cmdLine, client);
        } catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
        assertEquals("[stored list]\n[unused list]\n", log);
    }
    
    // n = 1
    @Test
    public void test_02() {
        Client client = new ClientStaff("xyli45-c@my.cityu.edu.hk", "12345678", "11111111");
        Rentable rentable = new Box("0001", new RentableStatusAvailable());
        requestManager.newRequest(new Request(client, rentable, new Date()));
        
        Command storeRentable = new CmdStoreRentable();
        String log = null;
        String[] cmdLine = {"BOX0001"};
        try {
            log = storeRentable.execute(cmdLine, client);
        } catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
        assertEquals("[stored list]\n"
        + String.format("> %s will be stored for %s\n", "BOX0001", "xyli45-c@my.cityu.edu.hk")
        + "[unused list]\n", log);
    }
    
    // exception 1
    @Test
    public void test_03() {
        Client client = new ClientStaff("xyli45-c@my.cityu.edu.hk", "12345678", "11111111");
        //Rentable rentable = new Box("0001", new RentableStatusAvailable());
        //requestManager.newRequest(new Request(client, rentable, new Date()));
        
        Command storeRentable = new CmdStoreRentable();
        String log = null;
        String[] cmdLine = {"BOX0001"};
        try {
            log = storeRentable.execute(cmdLine, client);
        } catch(Exception ex) {
            // assertEquals(String.format("%s has been stored for <%s>", "BOX0001", "xyli45-c@my.cityu.edu.hk"), ex.getMessage());
        }
    }
}
