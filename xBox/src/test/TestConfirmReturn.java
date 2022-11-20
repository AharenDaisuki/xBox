/**
 * 
 */
package test;

/**
 * @author lixiaoyang
 *
 */

import org.junit.Test;

import cmd.CmdConfirmReturn;
import cmd.Command;
import cmd.Undoable;
import data.Box;
import data.Client;
import data.ClientStaff;
import data.Rentable;
import data.RentableStatusOccupied;
import data.RentableStatusPending;
import data.RequestManager;
import utils.XBoxDate;
import data.RentableManager;
import data.RentableSearcher;
import data.RentableStatusAvailable;
import data.Record;
import data.RecordManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.After;
import org.junit.Before;

public class TestConfirmReturn {
    private RentableSearcher rentableSearcher = RentableSearcher.getInstance();
    // private RequestManager requestManager = RequestManager.getInstance();
    private RentableManager rentableManager = RentableManager.getInstance();
    private RecordManager recordManager = RecordManager.getInstance();
    // private XBoxDate systemDate = XBoxDate.getInstance();
    
    private Client[] allClients = new Client[10];
    private Rentable[] allRentables = new Rentable[10];
    private Record[] allRecords = new Record[10];
    private Command confirmReturn;
    private String log;
    
    @Before
    public void init() {
        this.confirmReturn = new CmdConfirmReturn();
        allClients[0] = new ClientStaff("xyli45-c@my.cityu.edu.hk", "12345678", "11111111");
        allRentables[0] = new Box("3141", new RentableStatusOccupied(new Date(), allClients[0])); // occupied
        allRentables[1] = new Box("9526", new RentableStatusPending(allClients[0]));
        rentableManager.addNewRentable(allRentables[0]);
        rentableManager.addNewRentable(allRentables[1]);
        allRecords[0] = new Record(allClients[0], allRentables[0], new Date()); // due 3 months 
        recordManager.insert(allRecords[0]);
        allRecords[1] = new Record(allClients[0], allRentables[1], new Date()); // due 3 months 
        recordManager.insert(allRecords[1]);
        this.log = null;
    }
    
    @After 
    public void clear() {
        this.confirmReturn = null;
        recordManager.delete(allRecords[0]);
        recordManager.delete(allRecords[1]);
        rentableManager.deleteRentable(allRentables[0]);
        rentableManager.deleteRentable(allRentables[1]);
    }
    
    // n = 0
    @Test
    public void test_01() {
        String[] cmdLine = {};
        try {
            log = confirmReturn.execute(cmdLine, allClients[0]);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } 
        assertEquals("[Checkin list]\n", log);
    }
    
    // n = 1
    @Test
    public void test_02() {
        String[] cmdLine = {"xyli45-c@my.cityu.edu.hk", "BOX9526"};
        try {
            log = confirmReturn.execute(cmdLine, allClients[0]);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } 
        assertEquals("[Checkin list]\n" 
        + String.format("> %s\n", "BOX9526"), log);
        assertEquals(RentableStatusAvailable.statusName, rentableSearcher.searchByKeyword("BOX9526").getStatusStr());
    }
    
    // exception => no such item
    @Test
    public void test_03() {
        String[] cmdLine = {"xyli45-c@my.cityu.edu.hk", "BOX9999"};
        try {
            log = confirmReturn.execute(cmdLine, allClients[0]);
        } catch (Exception ex) {
            assertEquals(String.format("Record [%s] is not found!", "BOX9999"), ex.getMessage());
        } 
    }
    
    // exception => no such request
    @Test
    public void test_04() {
        String[] cmdLine = {"xyli45-c@my.cityu.edu.hk", "BOX3141"};
        try {
            log = confirmReturn.execute(cmdLine, allClients[0]);
        } catch (Exception ex) {
            assertEquals(String.format("[Error] Checkin notification [%s] not found!", "BOX3141"), ex.getMessage());
        } 
    }
    
    // test undo & redo
    @Test
    public void test_05() {
        String[] cmdLine = {"xyli45-c@my.cityu.edu.hk", "BOX9526"};
        try {
            confirmReturn.execute(cmdLine, allClients[0]);
            log = Undoable.undoCmd();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        assertEquals("[Undo]\n" + String.format("> Confirm checkin [%s]\n", "BOX9526"), log);
        assertEquals(RentableStatusPending.statusName, rentableSearcher.searchByKeyword("BOX9526").getStatusStr());
        
        try {
            log = Undoable.redoCmd();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        assertEquals("[Redo]\n" + String.format("> Confirm checkin [%s]\n", "BOX9526"), log);
        assertEquals(RentableStatusAvailable.statusName, rentableSearcher.searchByKeyword("BOX9526").getStatusStr());
    }
}
