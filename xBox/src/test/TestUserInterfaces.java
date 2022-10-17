/**
 * 
 */
package test;

import data.*;
import ex.ExAccountExists;
import ex.ExEntryNotFound;
import ex.ExInvalidPassword;

// junit
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

// user interface
import xBox.UserInterfaces;


/**
 * @author lixiaoyang
 *
 */
public class TestUserInterfaces {
    // user interface
    UserInterfaces system = UserInterfaces.getInstance();
    // utils
    ClientSearcher clientSearcher = ClientSearcher.getInstance();
    
    @Test
    public void TestRegister() {
        String[] registerParams1 = {"AharenDaisuki@gmail.com", "12345678", "password", "student"};
        String[] registerParams2 = {"dongjiajie@gmail.com", "11111111", "whenisaylove", "staff"};
        String[] registerParams3 = {"dongjiajie@gmail.com", "11111111", "iforgotmypassword", "student"};
        // register
        try {
            system.register(registerParams1);
            system.register(registerParams2);
            system.register(registerParams3);
        } catch (ExAccountExists ex) {
            System.out.println(ex.getMessage());
        }
        // test
        assertEquals(true, clientSearcher.searchByKeyword("AharenDaisuki@gmail.com").verifyPassword("password"));
        assertEquals(true, clientSearcher.searchByKeyword("dongjiajie@gmail.com").verifyPassword("whenisaylove"));
        assertEquals(false, clientSearcher.searchByKeyword("dongjiajie@gmail.com").verifyPassword("iforgotpassword"));
    }
    
    @Test
    public void TestLoginValid() {
        String[] loginParams = {"dongjiajie@gmail.com", "whenisaylove"};
        try {
            system.login(loginParams);
        } catch (ExEntryNotFound ex) {
            System.out.println(ex.getMessage());
        } catch (ExInvalidPassword ex) {
            System.out.println(ex.getMessage());
        }
        // test
        assertEquals(true, system.verifyLoginUser("dongjiajie@gmail.com"));
    }
    
    @Test
    public void TestLoginWrongPassword() {
        String[] loginParams = {"AharenDaisuki@gmail.com", "iforgotmypassword"};
        try {
            system.login(loginParams);
        } catch (ExEntryNotFound ex) {
            System.out.println(ex.getMessage());
        } catch (ExInvalidPassword ex) {
            System.out.println(ex.getMessage());
        }
        // test
        assertEquals(false, system.verifyLoginUser("AharenDaisuki@gmail.com"));
        assertEquals(true, system.verifyLoginUser("dongjiajie@gmail.com"));
    }
    
    @Test
    public void TestLoginInexist() {
        String[] loginParams = {"xyli45_c@my.cityu.edu.hk", "Ahahahaha"};
        try {
            system.login(loginParams);
        } catch (ExEntryNotFound ex) {
            System.out.println(ex.getMessage());
        } catch (ExInvalidPassword ex) {
            System.out.println(ex.getMessage());
        }
        // test
        assertEquals(false, system.verifyLoginUser("AharenDaisuki@gmail.com"));
        assertEquals(true, system.verifyLoginUser("dongjiajie@gmail.com"));
    }
}
