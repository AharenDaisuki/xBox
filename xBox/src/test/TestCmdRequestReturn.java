/**
 * 
 */
package test;

/**
 * @author lixiaoyang
 *
 */

import org.junit.Test;

import cmd.CmdRequestReturn;
import cmd.Command;
import cmd.Undoable;
import data.Bag;
import data.Box;
import data.Client;
import data.ClientStaff;
import data.ClientStudent;
import data.Rentable;
import data.RentableStatusOccupied;
import data.RentableStatusPending;
import utils.XBoxDate;
import data.RentableManager;
import data.RentableSearcher;
import data.Record;
import data.RecordManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.After;
import org.junit.Before;

public class TestCmdRequestReturn {
    private RentableSearcher rentableSearcher = RentableSearcher.getInstance();
    private RentableManager rentableManager = RentableManager.getInstance();
    private RecordManager recordManager = RecordManager.getInstance();
    private XBoxDate systemDate = XBoxDate.getInstance();
    
    private String log;
    private Client[] allClients = new Client[10];
    private Rentable[] allRentables = new Rentable[10];
    private Record[] allRecords = new Record[10];
    private Command requestReturn;
    
    @Before
    public void init() {
        this.requestReturn = new CmdRequestReturn();
        allClients[0] = new ClientStaff("xyli45-c@my.cityu.edu.hk", "12345678", "11111111");
        allRentables[0] = new Bag("7777", new RentableStatusOccupied(new Date(), allClients[0])); // BOX0001 ~ lxy
        rentableManager.addNewRentable(allRentables[0]);
        allRecords[0] = new Record(allClients[0], allRentables[0], systemDate.getDayAfterNMonth("3")); // due 3 months 
        recordManager.insert(allRecords[0]);
        this.log = null;
    }
    
    @After 
    public void clear() {
        this.requestReturn = null;
        recordManager.delete(allRecords[0]);
        rentableManager.deleteRentable(allRentables[0]);
    }
    
    // n<= 0
    @Test
    public void test_01() {
        String[] cmdLine = {};
        try {
            log = requestReturn.execute(cmdLine, allClients[0]);
        } catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
        assertEquals("[Return list]\n", log);
    }
    
    // n = 1
    @Test
    public void test_02() {
        String[] cmdLine = {"BAG7777"};
        try {
            log = requestReturn.execute(cmdLine, allClients[0]);
        } catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
        assertEquals("[Return list]\n"
        + String.format("> send checkin notification [%s]\n", "BAG7777"), log);
        assertEquals(RentableStatusPending.statusName, rentableSearcher.searchByKeyword("BAG7777").getStatusStr());
    }
    
    // exception => no such request
    @Test
    public void test_03() {
        String[] cmdLine = {"BOX9999"};
        try {
            log = requestReturn.execute(cmdLine, allClients[0]);
        } catch(Exception ex) {
            assertEquals(String.format("Rent record [%s] not found", "BOX9999"), ex.getMessage());
        }
    }
    
    // exception => not the user
    @Test
    public void test_04() {
        allClients[1] = new ClientStudent("hacker@gmail.com", "12345678", "87654321");
        String[] cmdLine = {"BAG7777"};
        try {
            log = requestReturn.execute(cmdLine, allClients[1]);
        } catch(Exception ex) {
            assertEquals(String.format("Rent record [%s] not found", "BAG7777"), ex.getMessage());
        }
    }
    
    // exception => done before
    @Test 
    public void test_05() {
        String[] cmdLine = {"BAG7777"};
        try {
            log = requestReturn.execute(cmdLine, allClients[0]);
            log = requestReturn.execute(cmdLine, allClients[0]);
        } catch(Exception ex) {
            assertEquals(String.format("Checkin notification[%s]'s been sent", "BAG7777"), ex.getMessage());
        }
    }
    
    // undo & redo
    @Test
    public void test_06() {
        String[] cmdLine = {"BAG7777"};
        try {
            requestReturn.execute(cmdLine, allClients[0]);
            log = Undoable.undoCmd();
        } catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
        assertEquals("[Undo]\n" + String.format("> send checkin notification [%s]\n", "BAG7777"), log);
        assertEquals(RentableStatusOccupied.statusName, rentableSearcher.searchByKeyword("BAG7777").getStatusStr());
        
        try {
            log = Undoable.redoCmd();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        assertEquals("[Redo]\n" + String.format("> send checkin notification [%s]\n", "BAG7777"), log);
        assertEquals(RentableStatusPending.statusName, rentableSearcher.searchByKeyword("BAG7777").getStatusStr());
    }
}
