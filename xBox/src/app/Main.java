/**
 * 
 */
package app;

import io.login_or_register;
// import Xbox package
import xBox.*;

import java.io.IOException;

// import database
import data.Database;

/**
 * @author lixiaoyang
 * Description: Main class of the project
 * 
 */

public class Main {
    public static void main(String[] args) {
        // main class
        Xbox system = Xbox.getInstance();
        // data base
        Database database = Database.getInstance();
        // initialize
        try {
            database.initialize();
        } catch (IOException ex) {
            System.out.println("Fail to read json file!");;
        } 
        // io
        login_or_register.load();
    }
}
