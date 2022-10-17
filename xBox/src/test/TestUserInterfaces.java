/**
 * 
 */
package test;

import data.*;
import ex.ExAccountExists;
import ex.ExEntryNotFound;
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
public class TestUserInterfaces {
    // user interface
    private UserInterfaces system = UserInterfaces.getInstance();
    // utils
    private ClientSearcher clientSearcher = ClientSearcher.getInstance();
    private RentableManager rentableManager = RentableManager.getInstance();
    private RequestSearcher requestSearcher = RequestSearcher.getInstance();
    
    
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
        String log = system.request(requestParams);
        System.out.println(log);
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
        String[] requestParams = {"BAG", "10", "9"};
        String log = system.request(requestParams);
        System.out.println(log);
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
}
