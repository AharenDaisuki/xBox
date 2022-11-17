/**
 * 
 */
package test;

/**
 * @author xyli45
 *
 */

import org.junit.Test;
//import org.junit.runners.MethodSorters;
//import org.junit.FixMethodOrder;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import cmd.Command;
import cmd.CmdRequestRentable;

import data.Client;
import data.Rentable;
import data.RentableStatus;
import data.RentableManager;
import data.RentableStatusAvailable;
import utils.XBoxDate;

public class TestCmdRequestRentable {
    private class Client_stub extends Client{
        public Client_stub(String email_, String phoneNo_, String password_) {
            super(email_, phoneNo_, password_);
            // TODO Auto-generated constructor stub
        }

        @Override
        public double getDiscount() {
            // TODO Auto-generated method stub
            return 0.9;
        }

        @Override
        public int getMaxBorrowedCount() {
            // TODO Auto-generated method stub
            return 10;
        }
    }
    
    private class Rentable_stub extends Rentable{

        public Rentable_stub(String rentableID_, RentableStatus status_) {
            super("TYP" + rentableID_ , status_);
            // TODO Auto-generated constructor stub
        }

        @Override
        public String getType() {
            // TODO Auto-generated method stub
            return "TYP";
        }

        @Override
        public double getPrice() {
            // TODO Auto-generated method stub
            return 10.0;
        }
        
    }
    
    private RentableManager rentableManager = RentableManager.getInstance();
    private XBoxDate systemDate = XBoxDate.getInstance();
    
    // n > max count(10)
    @Test
    public void test_01() {
        Command requestRentable = new CmdRequestRentable();
        try {
            Client_stub client = new Client_stub("xyli45-c@my.cityu.edu.hk", "12345678", "11111111");
            String[] cmdLine = {
                    "TYP", // dummy type
                    "11", // number > max count
                    "1" // month
            };
            String log = requestRentable.execute(cmdLine, client);
        }catch(Exception ex) {
            assertEquals("[Error] No more than 10 TYP per user!", ex.getMessage());
        }
    }
    
    // n <= 0
    @Test 
    public void test_02() {
        Command requestRentable = new CmdRequestRentable();
        String log = null;
        try {
            Client_stub client = new Client_stub("xyli45-c@my.cityu.edu.hk", "12345678", "11111111");
            String[] cmdLine = {
                    "TYP", // dummy type
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
        Command requestRentable = new CmdRequestRentable();
        String log = null;
        rentableManager.addNewRentable(new Rentable_stub("0001", new RentableStatusAvailable()));
        
        try {
            Client_stub client = new Client_stub("xyli45-c@my.cityu.edu.hk", "12345678", "11111111");
            String[] cmdLine = {
                    "TYP", // dummy type
                    "1", // number = 1
                    "1" // month
            };
            log = requestRentable.execute(cmdLine, client);
        }catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
        assertEquals("[request list]\n" + String.format("> %-10s%tF\n", "TYP0001", systemDate.getDayAfterNMonth("1")), log);
    }
}
