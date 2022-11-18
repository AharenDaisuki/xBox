/**
 * 
 */
package test;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;

/**
 * @author lixiaoyang
 *
 */

import org.junit.Test;

import cmd.CmdStoreRentable;
import cmd.Command;
import cmd.Undoable;

import static org.junit.jupiter.api.Assertions.assertEquals;

import data.Box;
import data.Client;
import data.ClientStaff;
import data.ClientStudent;
import data.Rentable;
import data.RentableManager;
import data.RentableSearcher;
import data.RentableStatusOccupied;
import data.RentableStatusRequested;
import data.Request;
import data.RequestManager;

import java.util.Date;

@FixMethodOrder
public class TestCmdStoreRentable {
    private RequestManager requestManager = RequestManager.getInstance();
    private RentableManager rentableManager = RentableManager.getInstance();
    private RentableSearcher rentableSearcher = RentableSearcher.getInstance();
    
    private String log;
    private Client[] allClients = new Client[10];
    private Rentable[] allRentables = new Rentable[10];
    private Request[] allRequests = new Request[10];
    private Command storeRentable; 
    
    @Before
    public void init() {
        this.storeRentable = new CmdStoreRentable();
        allClients[0] = new ClientStaff("xyli45-c@my.cityu.edu.hk", "12345678", "11111111");
        allRentables[0] = new Box("0001", new RentableStatusRequested(allClients[0])); // BOX0001 ~ lxy
        rentableManager.addNewRentable(allRentables[0]);
        allRequests[0] = new Request(allClients[0], allRentables[0], new Date());
        requestManager.newRequest(allRequests[0]);
        this.log = null;
    }
    
    @After 
    public void clear() {
        this.storeRentable = null;
        requestManager.removeRequest(allRequests[0]);
        rentableManager.deleteRentable(allRentables[0]);
    }
    
    // n<= 0
    @Test
    public void test_01() {
        String[] cmdLine = {};
        try {
            log = storeRentable.execute(cmdLine, allClients[0]);
        } catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
        assertEquals("[stored list]\n[unused list]\n" + String.format("> %s is requested but not used\n", "BOX0001"), log);
    }
    
    // n = 1
    @Test
    public void test_02() {
        //allRentables[1] = new Box("0002", new RentableStatusRequested(allClients[0])); // BOX0001 ~ lxy
        //allRequests[1] = new Request(allClients[0], allRentables[1], new Date());
        //requestManager.newRequest(allRequests[1]);
        String[] cmdLine = {"BOX0001"};
        try {
            log = storeRentable.execute(cmdLine, allClients[0]);
        } catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
        assertEquals("[stored list]\n"
        + String.format("> %s will be stored for %s\n", "BOX0001", "xyli45-c@my.cityu.edu.hk")
        + "[unused list]\n", log);
        assertEquals(RentableStatusOccupied.statusName, rentableSearcher.searchByKeyword("BOX0001").getStatusStr());
    }
    
    // exception => no such request
    @Test
    public void test_03() {
        String[] cmdLine = {"BOX9999"};
        try {
            log = storeRentable.execute(cmdLine, allClients[0]);
        } catch(Exception ex) {
            assertEquals(String.format("Request[%s] is not found", "BOX9999"), ex.getMessage());
        }
    }
    
    // exception => not the user
    @Test
    public void test_04() {
        allClients[1] = new ClientStudent("hacker@gmail.com", "12345678", "87654321");
        String[] cmdLine = {"BOX0001"};
        try {
            log = storeRentable.execute(cmdLine, allClients[1]);
        } catch(Exception ex) {
            assertEquals(String.format("Request[%s] is not found", "BOX0001"), ex.getMessage());
        }
    }
    
    // undo & redo
    @Test
    public void test_05() {
        String[] cmdLine = {"BOX0001"};
        try {
            storeRentable.execute(cmdLine, allClients[0]);
            log = Undoable.undoCmd();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        assertEquals("[Undo]\n" + String.format("> store %s\n", "BOX0001"), log);
        assertEquals(RentableStatusRequested.statusName, rentableSearcher.searchByKeyword("BOX0001").getStatusStr());
        
        try {
            log = Undoable.redoCmd();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        assertEquals("[Redo]\n" + String.format("> store %s\n", "BOX0001"), log);
        assertEquals(RentableStatusOccupied.statusName, rentableSearcher.searchByKeyword("BOX0001").getStatusStr());
    }
}
