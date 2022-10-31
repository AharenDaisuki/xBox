/**
 * 
 */
package test;

import data.*;
import data.Record;
import ex.ExAccountExists;
import ex.ExEntryNotFound;
import ex.ExInfoMissing;
import ex.ExInvalidPassword;
// import ex.ExNoSufficientRentable;
// junit
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.junit.FixMethodOrder;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

// user interface
import xBox.UserInterfaces;


/**
 * @author lixiaoyang
 *
 */

@FixMethodOrder(MethodSorters.JVM)
@Deprecated
public class TestUserInterfaces {
    // user interface
    private UserInterfaces system = UserInterfaces.getInstance();
    // utils
    private ClientSearcher clientSearcher = ClientSearcher.getInstance();
    private RentableSearcher rentableSearcher = RentableSearcher.getInstance();
    private RentableManager rentableManager = RentableManager.getInstance();
    private RequestSearcher requestSearcher = RequestSearcher.getInstance();
    private RecordSearcher recordSearcher = RecordSearcher.getInstance();
    
    
    @Test
    public void TestRegister() {
        String[] registerParams1 = {"AharenDaisuki@gmail.com", "12345678", "password", "student"}; // valid
        String[] registerParams2 = {"dongjiajie@gmail.com", "11111111", "whenisaylove", "staff"}; // valid
        String[] registerParams3 = {"dongjiajie@gmail.com", "11111111", "iforgotmypassword", "student"};
        // register
        try {
            System.out.println(system.register(registerParams1));
            System.out.println(system.register(registerParams2));
            System.out.println(system.register(registerParams3));
        } catch (ExAccountExists ex) {
            System.out.println(ex.getMessage());
        } catch (ExInfoMissing e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // test
        Client client1 = clientSearcher.searchByKeyword("AharenDaisuki@gmail.com");
        Client client2 = clientSearcher.searchByKeyword("dongjiajie@gmail.com");
        assertEquals(false, (client1 == null));
        assertEquals(false, (client2 == null));
        assertEquals(true, client1.verifyPassword("password"));
        assertEquals(true, client2.verifyPassword("whenisaylove"));
        assertEquals(false, client2.verifyPassword("iforgotpassword"));
        System.out.println(String.format("***%s***\n", "Test Register"));
    }
    
    @Test
    public void TestLoginValid() {
        String[] loginParams = {"dongjiajie@gmail.com", "whenisaylove"}; // login
        try {
            System.out.println(system.login(loginParams));
        } catch (ExEntryNotFound ex) {
            System.out.println(ex.getMessage());
        } catch (ExInvalidPassword ex) {
            System.out.println(ex.getMessage());
        }
        // test
        assertEquals(false, (system.getUser() == null));
        assertEquals(true, system.verifyLoginUser("dongjiajie@gmail.com"));
        System.out.println(String.format("***%s***\n", "Test Login1"));
    }
    
    @Test
    public void TestLoginWrongPassword() {
        String[] loginParams = {"AharenDaisuki@gmail.com", "iforgotmypassword"};
        try {
            System.out.println(system.login(loginParams));
        } catch (ExEntryNotFound ex) {
            System.out.println(ex.getMessage());
        } catch (ExInvalidPassword ex) {
            System.out.println(ex.getMessage());
        }
        // test
        assertEquals(false, system.verifyLoginUser("AharenDaisuki@gmail.com"));
        assertEquals(true, system.verifyLoginUser("dongjiajie@gmail.com"));
        System.out.println(String.format("***%s***\n", "Test Login2"));
    }
    
    @Test
    public void TestLoginInexist() {
        String[] loginParams = {"xyli45_c@my.cityu.edu.hk", "Ahahahaha"};
        try {
            System.out.println(system.login(loginParams));
        } catch (ExEntryNotFound ex) {
            System.out.println(ex.getMessage());
        } catch (ExInvalidPassword ex) {
            System.out.println(ex.getMessage());
        }
        // test
        assertEquals(false, system.verifyLoginUser("AharenDaisuki@gmail.com"));
        assertEquals(true, system.verifyLoginUser("dongjiajie@gmail.com"));
        System.out.println(String.format("***%s***\n", "Test Login3"));
    }
    
    @Test
    public void TestRequestValid() {
        // TODO: replaced by json
        rentableManager.addNewRentable(new Box("1234", new RentableStatusAvailable()));
        rentableManager.addNewRentable(new Box("3452", new RentableStatusAvailable()));
        rentableManager.addNewRentable(new Box("4883", new RentableStatusAvailable()));
        rentableManager.addNewRentable(new Bag("1391", new RentableStatusAvailable()));
        rentableManager.addNewRentable(new Bag("3251", new RentableStatusAvailable()));
        rentableManager.addNewRentable(new Bag("8837", new RentableStatusAvailable()));
        // request
        String[] requestParams = {"BOX", "2", "9"}; // 2 boxes for 9 months
        //String log = system.request(requestParams);
        //System.out.println(log);
        // test
        Client user = system.getUser(); // test only
        ArrayList<Request> requestList = requestSearcher.searchAllByKeyword(user);
        assertEquals(2, requestList.size()); // test number
        for(Request request : requestList) {
            assertEquals(user, request.getClient()); // test user
            // assertEquals("BOX", request.getRentable().getType()); // test type
            assertEquals(RentableStatusRequested.statusName, request.getRentable().getStatusStr()); // test status
        }
        System.out.println(String.format("***%s***\n", "Test Request1"));
    }
    
    @Test
    public void TestRequestInsufficient() {
        String[] requestParams = {"BAG", "10", "1"};
        //String log = system.request(requestParams);
        //System.out.println(log);
        // test
        Client user = system.getUser();
        ArrayList<Request> requestList = requestSearcher.searchAllByKeyword(user);
        assertEquals(2 + 3, requestList.size()); // only 3 bags left
        for(Request request : requestList) {
            assertEquals(user, request.getClient());
            // assertEquals("BAG", request.getRentable().getType());
            assertEquals(RentableStatusRequested.statusName, request.getRentable().getStatusStr());
        }
        System.out.println(String.format("***%s***\n", "Test Request2"));
    }
    
    @Test
    public void TestStoreValid() {
        String[] storeParams = {"BOX1234"};
        // String log = system.store(storeParams);
        // System.out.println(log);
        // test
        ArrayList<Record> recordList = recordSearcher.searchAllByKeyword("dongjiajie@gmail.com");
        assertEquals(1, recordList.size());
        for(Record record : recordList) {
            assertEquals(RentableStatusOccupied.statusName, record.getRentable().getStatusStr());
        }
        System.out.println(String.format("***%s***\n", "Test Store1"));
    }
    
    @Test
    public void TestStoreInvalid() {
        String[] storeParams = {"BOX3452", "BOX6666"};
        // TODO: handle 
        // String[] storeParams = {"BOX6666", "BOX3452"}; 
        // String log = system.store(storeParams);
        // System.out.println(log);
        // test
        ArrayList<Record> recordList = recordSearcher.searchAllByKeyword("dongjiajie@gmail.com");
        assertEquals(1 + 1, recordList.size());
        for(Record record : recordList) {
            assertEquals(RentableStatusOccupied.statusName, record.getRentable().getStatusStr());
        }
        System.out.println(String.format("***%s***\n", "Test Store2"));
    }
    
    @Test 
    public void TestStoreUndoRedo() {
        // initial
        Client client = clientSearcher.getInstance().searchByKeyword("dongjiajie@gmail.com");
        ArrayList<Request> requestList = requestSearcher.searchAllByKeyword(client);
        ArrayList<Record> recordList = recordSearcher.searchAllByKeyword("dongjiajie@gmail.com");
        assertEquals(0, requestList.size());
        assertEquals(2, recordList.size());
        // undo
        //String undoLog = system.undo();
        // Client client = clientSearcher.getInstance().searchByKeyword("dongjiajie@gmail.com");
        ArrayList<Request> requestListUndo = requestSearcher.searchAllByKeyword(client);
        ArrayList<Record> recordListUndo = recordSearcher.searchAllByKeyword("dongjiajie@gmail.com");
        assertEquals(1, requestListUndo.size());
        assertEquals(1, recordListUndo.size());
        // redo
        //String redoLog = system.redo();
        ArrayList<Request> requestListRedo = requestSearcher.searchAllByKeyword(client);
        ArrayList<Record> recordListRedo = recordSearcher.searchAllByKeyword("dongjiajie@gmail.com");
        assertEquals(0, requestListRedo.size());
        assertEquals(2, recordListRedo.size());
        
    }
    
    @Test
    public void TestReturnValid() {
        String[] returnParams = {"BOX1234"};
        // String log = system.unload(returnParams);
        // System.out.println(log);
        ArrayList<Record> recordList = recordSearcher.searchAllByKeyword("dongjiajie@gmail.com");
        assertEquals(2, recordList.size());
        assertEquals(RentableStatusPending.statusName, rentableSearcher.searchByKeyword("BOX1234").getStatusStr());
        System.out.println(String.format("***%s***\n", "Test Return1"));
    }
    
    @Test
    public void TestReturnInvalid() {
        String[] returnParams = {"BOX2333"}; 
        // String log = system.unload(returnParams);
        // System.out.println(log);
        // assertEquals();
        System.out.println(String.format("***%s***\n", "Test Return2"));
    }
    
    @Test
    public void TestUndo() {
        //String log1 = system.undo();
        //System.out.println(log1);
        //String log2 = system.undo();
        //System.out.println(log2);
        // test
        ArrayList<Record> recordList = recordSearcher.searchAllByKeyword("dongjiajie@gmail.com");
        assertEquals(1 + 1, recordList.size());
        for(Record record : recordList) {
            assertEquals(RentableStatusOccupied.statusName, record.getRentable().getStatusStr());
        }
        System.out.println(String.format("***%s***\n", "Test Undo"));
    }
    
    @Test
    public void TestRedo() {
        //String log = system.redo();
        //System.out.println(log);
        // test
        ArrayList<Record> recordList = recordSearcher.searchAllByKeyword("dongjiajie@gmail.com");
        assertEquals(2, recordList.size());
        assertEquals(RentableStatusPending.statusName, rentableSearcher.searchByKeyword("BOX1234").getStatusStr());
        System.out.println(String.format("***%s***\n", "Test Undo"));
    }
    
    @Test
    public void TestSummary() {
        String log = system.summary(null);
        System.out.println(log);
        System.out.println("***End Test***\n");
    }
}
