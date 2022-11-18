/**
 * 
 */
package test;

/**
 * @author lixiaoyang
 *
 */

import org.junit.Test;

import cmd.CmdConfirmPayment;
import cmd.Command;
import cmd.Undoable;
import data.Bag;
import data.Box;
import data.Client;
import data.ClientStaff;
import data.Rentable;
import data.RentableStatusOccupied;
import data.RentableStatusRequested;
import data.Request;
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

public class TestConfirmPayment {
    private RentableSearcher rentableSearcher = RentableSearcher.getInstance();
    private RequestManager requestManager = RequestManager.getInstance();
    private RentableManager rentableManager = RentableManager.getInstance();
    private RecordManager recordManager = RecordManager.getInstance();
    private XBoxDate systemDate = XBoxDate.getInstance();
    
    private Client[] allClients = new Client[10];
    private Rentable[] allRentables = new Rentable[10];
    private Request[] allRequests = new Request[10];
    private Record[] allRecords = new Record[10];
    private Command confirmPayment;
    private String log;
    
    @Before
    public void init() {
        this.confirmPayment = new CmdConfirmPayment();
        allClients[0] = new ClientStaff("xyli45-c@my.cityu.edu.hk", "12345678", "11111111");
        allRentables[0] = new Box("2333", new RentableStatusOccupied(new Date(), allClients[0])); // occupied
        allRentables[1] = new Bag("1234", new RentableStatusRequested(allClients[0])); // requested
        rentableManager.addNewRentable(allRentables[0]);
        rentableManager.addNewRentable(allRentables[1]);
        allRecords[0] = new Record(allClients[0], allRentables[0], systemDate.getDayAfterNMonth("3")); // due 3 months 
        recordManager.insert(allRecords[0]);
        allRequests[0] = new Request(allClients[0], allRentables[1], new Date());
        requestManager.newRequest(allRequests[0]);
        this.log = null;
    }
    
    @After 
    public void clear() {
        this.confirmPayment = null;
        recordManager.delete(allRecords[0]);
        requestManager.removeRequest(allRequests[0]);
        rentableManager.deleteRentable(allRentables[0]);
        rentableManager.deleteRentable(allRentables[1]);
    }
    
    // test payment
    @Test
    public void test_01() {
        String[] cmdLine = {"xyli45-c@my.cityu.edu.hk"};
        try {
            log = confirmPayment.execute(cmdLine, allClients[0]);
        } catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
        assertEquals("[Payment]\n" 
        + String.format(">%-5s\t$%.2f\n", "BOX2333", allRentables[0].getPrice())
        + String.format("\ndiscount: %.0f percent off\n", (1-allClients[0].getDiscount()) * 100)
        + String.format("total: $%.2f\n", allRentables[0].getPrice() * allClients[0].getDiscount()), log);
        assertEquals(true, allRecords[0].getPaymentStatus());
        assertEquals(RentableStatusAvailable.statusName, rentableSearcher.searchByKeyword("BAG1234").getStatusStr());
    } 
    
    // test payment
    @Test
    public void test_02() {
        String[] cmdLine = {"xyli45-c@my.cityu.edu.hk"};
        try {
            log = confirmPayment.execute(cmdLine, allClients[0]);
            log = confirmPayment.execute(cmdLine, allClients[0]);
        } catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
        assertEquals("[Payment]\n" 
        + String.format("\ndiscount: %.0f percent off\n", (1-allClients[0].getDiscount()) * 100)
        + String.format("total: $%.2f\n", 0.0), log);
    }
    
    // test undo redo
    @Test
    public void test_03() {
        String[] cmdLine = {"xyli45-c@my.cityu.edu.hk"};
        try {
            log = confirmPayment.execute(cmdLine, allClients[0]);
            log = Undoable.undoCmd();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } 
        assertEquals("[Undo]\n" 
        + String.format("> confirm request[%s] unused\n", "BAG1234"), log);
        assertEquals(RentableStatusRequested.statusName, rentableSearcher.searchByKeyword("BAG1234").getStatusStr());
        
        try {
            log = Undoable.redoCmd();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        assertEquals("[Redo]\n" 
        + String.format("> confirm request[%s] unused\n", "BAG1234"), log);
        assertEquals(RentableStatusAvailable.statusName, rentableSearcher.searchByKeyword("BAG1234").getStatusStr());
    }
}
