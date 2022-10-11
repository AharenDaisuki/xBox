/**
 * 
 */
package test;

import data.*;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import cmd.CmdRequestRentable;
import cmd.Undoable;

/**
 * @author lixiaoyang
 * Description: test class for CmdRequestRentable
 */
public class TestCmdReqRentable {
    RentableManager rentableManager = RentableManager.getInstance();
    RequestSearcher requestSearcher = RequestSearcher.getInstance();
    @Test
    public void TestExecute() {
        // TODO: replaced by json
        // mimic
        rentableManager.addNewRentable(new Box("0001", new RentableStatusAvailable()));
        rentableManager.addNewRentable(new Box("0002", new RentableStatusAvailable()));
        rentableManager.addNewRentable(new Box("0003", new RentableStatusAvailable()));
        rentableManager.addNewRentable(new Bag("0001", new RentableStatusAvailable()));
        rentableManager.addNewRentable(new Bag("0002", new RentableStatusAvailable()));
        // user1
        Client user1 = new ClientStudent("AharenDaisuki@gmail.com", "12345678", "");
        Client user2 = new ClientStaff("Jacky@gmail.com", "11111111", "password");
        String[] cmdLine1 = {"BOX", "2", "9"}; // borrow 2 boxes for 9 months
        String[] cmdLine2 = {"BAG", "2", "9"}; // borrow 2 bags for 9 months
        //String[] cmdLine3 = {"BOX", "1", "6"};
        String[] cmdLine3 = {"BOX", "2", "6"};
        (new CmdRequestRentable()).execute(cmdLine1, user1);
        (new CmdRequestRentable()).execute(cmdLine2, user1);
        (new CmdRequestRentable()).execute(cmdLine3, user2);
        // test 
        ArrayList<Request> user1Requests = requestSearcher.searchByClient(user1);
        for(Request request : user1Requests) {
            Rentable rentable = request.getRentable();
            assertEquals(RentableStatusRequested.statusName, rentable.getStatusStr());
        }
        
        Request user2Request = requestSearcher.searchByRentableID("BOX0003");
        assertEquals(user2, user2Request.getClient());
    }
    
    @Test
    public void TestUndo() {
        rentableManager.addNewRentable(new Box("0001", new RentableStatusAvailable()));
        rentableManager.addNewRentable(new Box("0002", new RentableStatusAvailable()));
        rentableManager.addNewRentable(new Box("0003", new RentableStatusAvailable()));
        rentableManager.addNewRentable(new Bag("0001", new RentableStatusAvailable()));
        rentableManager.addNewRentable(new Bag("0002", new RentableStatusAvailable()));
        Client user1 = new ClientStudent("AharenDaisuki@gmail.com", "12345678", "");
        Client user2 = new ClientStaff("Jacky@gmail.com", "11111111", "password");
        String[] cmdLine1 = {"BOX", "2", "9"}; // borrow 2 boxes for 9 months
        String[] cmdLine2 = {"BAG", "2", "9"}; // borrow 2 bags for 9 months
        String[] cmdLine3 = {"BOX", "1", "6"};
        (new CmdRequestRentable()).execute(cmdLine1, user1);
        (new CmdRequestRentable()).execute(cmdLine2, user1);
        (new CmdRequestRentable()).execute(cmdLine3, user2);
        Undoable.undoCmd();
        Undoable.undoCmd();
        // test
        ArrayList<Request> user1Requests = requestSearcher.searchByClient(user1);
        ArrayList<Request> user2Requests = requestSearcher.searchByClient(user2);
        assertEquals(user2Requests, null);
    }
}
