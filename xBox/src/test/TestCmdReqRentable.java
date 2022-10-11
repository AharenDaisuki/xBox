/**
 * 
 */
package test;

import data.*;
import ex.ExNoSufficientRentable;

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
    RentableSearcher rentableSearcher = RentableSearcher.getInstance();
    RequestManager requestManager = RequestManager.getInstance();
    RequestSearcher requestSearcher = RequestSearcher.getInstance();
    ClientManager clientManager = ClientManager.getInstance();
    ClientSearcher clientSearcher = ClientSearcher.getInstance();
    /*
    @Test
    public void TestExecute() {
        RentableManager rentableManager = RentableManager.getInstance();
        RequestSearcher requestSearcher = RequestSearcher.getInstance();
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
        String[] cmdLine3 = {"BOX", "1", "6"}; // no overflow
        //String[] cmdLine3 = {"BOX", "2", "6"}; // overflow
        try {
            (new CmdRequestRentable()).execute(cmdLine1, user1);
            (new CmdRequestRentable()).execute(cmdLine2, user1);
            (new CmdRequestRentable()).execute(cmdLine3, user2);
        } catch (ExNoSufficientRentable ex) {
            System.out.println(ex.getMessage());
        }
        // test 
        ArrayList<Request> user1Requests = requestSearcher.searchAllByKeyword(user1);
        for(Request request : user1Requests) {
            Rentable rentable = request.getRentable();
            assertEquals(RentableStatusRequested.statusName, rentable.getStatusStr());
        }
        
        Request user2Request = requestSearcher.searchByKeyword("BOX0003");
        assertEquals(user2, user2Request.getClient());
    }
    
    @Test
    public void TestUndo() {
        rentableManager.addNewRentable(new Box("0004", new RentableStatusAvailable()));
        rentableManager.addNewRentable(new Box("0005", new RentableStatusAvailable()));
        rentableManager.addNewRentable(new Box("0006", new RentableStatusAvailable()));
        rentableManager.addNewRentable(new Bag("0003", new RentableStatusAvailable()));
        rentableManager.addNewRentable(new Bag("0004", new RentableStatusAvailable()));
        Client user1 = new ClientStudent("dongjiajie@gmail.com", "23333333", "");
        Client user2 = new ClientStaff("keainie@gmail.com", "31415926", "");
        String[] cmdLine1 = {"BOX", "2", "9"}; // borrow 2 boxes for 9 months
        String[] cmdLine2 = {"BAG", "2", "9"}; // borrow 2 bags for 9 months
        String[] cmdLine3 = {"BOX", "1", "6"};
        try {
            (new CmdRequestRentable()).execute(cmdLine1, user1);
            (new CmdRequestRentable()).execute(cmdLine2, user1);
            (new CmdRequestRentable()).execute(cmdLine3, user2);
        } catch (ExNoSufficientRentable ex) {
            System.out.println(ex.getMessage());
        }
        Undoable.undoCmd();
        Undoable.undoCmd();
        // test
        ArrayList<Request> user1Requests = requestSearcher.searchAllByKeyword(user1);
        ArrayList<Request> user2Requests = requestSearcher.searchAllByKeyword(user2);
        for(Request request : user1Requests) {
            Rentable rentable = request.getRentable();
            assertEquals(RentableStatusRequested.statusName, rentable.getStatusStr());
        }
        assertEquals(0, user2Requests.size());
        assertEquals(RentableStatusAvailable.statusName, rentableSearcher.searchByKeyword("BOX0006").getStatusStr());
    }*/
}
