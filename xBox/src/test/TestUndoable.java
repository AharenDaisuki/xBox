/**
 * 
 */
package test;

/**
 * @author lixiaoyang
 *
 */

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.Test;
import org.junit.After;
import org.junit.Before;

import cmd.CmdRequestRentable;
import cmd.Command;
import cmd.Undoable;
import data.Box;
import data.Client;
import data.ClientStaff;
import data.RentableManager;
import data.RentableStatusAvailable;
import ex.ExEmptyVector;

public class TestUndoable {
    
    // nothing to undo
    @Test
    public void test_01() {
        try {
            Undoable.undoCmd();
        } catch (Exception ex) {
            assertEquals("[Error] Nothing to undo!", ex.getMessage());
        }
    }

    // nothing to redo
    @Test
    public void test_02() {
        try {
            Undoable.redoCmd();
        } catch (Exception ex) {
            assertEquals("[Error] Nothing to redo!", ex.getMessage());
        }
    }
    
    // undo & redo
    @Test
    public void test_03() {
        try {
            RentableManager rentableManager = RentableManager.getInstance();
            Command cmd = new CmdRequestRentable();
            String[] cmdLine = {"BOX", "1", "2"};
            Client client = new ClientStaff("xyli45-c@my.cityu.edu.hk", "12345678", "11111111");
            rentableManager.addNewRentable(new Box("0624", new RentableStatusAvailable()));
            cmd.execute(cmdLine, client);
            Undoable.undoCmd();
            Undoable.redoCmd();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
