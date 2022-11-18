/**
 * 
 */
package test;

/**
 * @author xyli45
 *
 */

import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.junit.FixMethodOrder;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.After;
import org.junit.Before;

import cmd.Command;
import cmd.Undoable;
import cmd.CmdRequestRentable;
import data.Bag;
import data.Client;
import data.ClientStudent;
import data.Rentable;
import data.RentableManager;
import data.RentableSearcher;
import data.RentableStatusAvailable;
import data.RentableStatusRequested;
import utils.XBoxDate;

@FixMethodOrder
public class TestCmdRequestRentable {
    private RentableManager rentableManager = RentableManager.getInstance();
    private RentableSearcher rentableSearcher = RentableSearcher.getInstance();
    private XBoxDate systemDate = XBoxDate.getInstance();
    
    private Command requestRentable;
    private Client client;
    private String log;
    private Rentable rentable1 = new Bag("0001", new RentableStatusAvailable());
    private Rentable rentable2 = new Bag("0002", new RentableStatusAvailable());
    private Rentable rentable3 = new Bag("0003", new RentableStatusAvailable());
    
    
    @Before
    public void init() {
        this.requestRentable = new CmdRequestRentable();
        this.client = new ClientStudent("xyli45-c@my.cityu.edu.hk", "12345678", "11111111");
        this.log = null;
    }
    
    @After
    public void clear() {
        this.requestRentable = null;
        this.client = null;
    }
    
    // n > max count(10)
    @Test
    public void test_01() {
        //Command requestRentable = new CmdRequestRentable();
        try {
            // Client client = new ClientStudent("xyli45-c@my.cityu.edu.hk", "12345678", "11111111");
            String[] cmdLine = {
                    "BAG", // dummy type
                    "11", // number > max count
                    "1" // month
            };
            log = requestRentable.execute(cmdLine, client);
        }catch(Exception ex) {
            assertEquals("[Error] No more than 10 BAG per user!", ex.getMessage());
        }
    }
    
    // n <= 0
    @Test 
    public void test_02() {
        //Command requestRentable = new CmdRequestRentable();
        try {
            //Client client = new ClientStudent("xyli45-c@my.cityu.edu.hk", "12345678", "11111111");
            String[] cmdLine = {
                    "BAG", // dummy type
                    "-1", // number <= 0
                    "1" // month
            };
            log = requestRentable.execute(cmdLine, client);
        }catch(Exception ex) {
            //assertEquals(ex.getMessage(), "[Error] No more than 10 TYPE per user!");
            System.out.println(ex.getMessage());
        }
        assertEquals("[request list]\n", log);
    }
    
    // n = 1
    @Test
    public void test_03() {
        //Command requestRentable = new CmdRequestRentable();
        rentableManager.addNewRentable(rentable1);
        
        try {
            //Client client = new ClientStudent("xyli45-c@my.cityu.edu.hk", "12345678", "11111111");
            String[] cmdLine = {
                    "BAG", // dummy type
                    "1", // number = 1
                    "1" // month
            };
            log = requestRentable.execute(cmdLine, client);
        }catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
        assertEquals("[request list]\n" + String.format("> %-10s%tF\n", "BAG0001", systemDate.getDayAfterNMonth("1")), log);
        assertEquals(RentableStatusRequested.statusName, rentable1.getStatusStr());
    }
    
    // n > 1
    @Test
    public void test_04() {
        //Command requestRentable = new CmdRequestRentable();
        rentableManager.addNewRentable(rentable2);
        rentableManager.addNewRentable(rentable3);
        
        try {
            //Client client = new ClientStudent("xyli45-c@my.cityu.edu.hk", "12345678", "11111111");
            String[] cmdLine = {
                    "BAG", // dummy type
                    "2", // number > 1
                    "1" // month
            };
            log = requestRentable.execute(cmdLine, client);
        }catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
        assertEquals("[request list]\n" 
        + String.format("> %-10s%tF\n", "BAG0002", systemDate.getDayAfterNMonth("1"))
        + String.format("> %-10s%tF\n", "BAG0003", systemDate.getDayAfterNMonth("1")), log);
        assertEquals(RentableStatusRequested.statusName, rentable2.getStatusStr());
        assertEquals(RentableStatusRequested.statusName, rentable3.getStatusStr());
    }
    
    // test undo
    @Test
    public void test_05() {
        try {
            log = Undoable.undoCmd();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
        assertEquals("[Undo]\n" 
        + String.format("> send request %s\n", "BAG0002")
        + String.format("> send request %s\n", "BAG0003"), log);
        
        assertEquals(RentableStatusAvailable.statusName, rentableSearcher.searchByKeyword("BAG0002").getStatusStr());
        assertEquals(RentableStatusAvailable.statusName, rentableSearcher.searchByKeyword("BAG0003").getStatusStr());
        // assertEquals(2, Undoable.undoList.size());
        // assertEquals(1, Undoable.redoList.size());
    }
    
    // test redo
    @Test
    public void test_06() {
        try {
            log = Undoable.redoCmd();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        assertEquals("[Redo]\n" 
        + String.format("> send request %s\n", "BAG0002")
        + String.format("> send request %s\n", "BAG0003"), log);
        assertEquals(RentableStatusRequested.statusName, rentableSearcher.searchByKeyword("BAG0002").getStatusStr());
        assertEquals(RentableStatusRequested.statusName, rentableSearcher.searchByKeyword("BAG0003").getStatusStr());
    }
}
