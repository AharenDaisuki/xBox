/**
 * 
 */
package app;

import io.login_or_register;
// import Xbox package
import xBox.*;

import java.io.File;
import java.io.IOException;

// import database
import data.Database;

/**
 * @file Main.java
 * 
 * This file defines the main class of the project, which is the very start to run the program.
 * 
 */

public class Main {
    public static void main(String[] args) {
        String[] prd_files = {
                args[0], // rentable
                args[1], // record
                args[2], // client
                args[3] // request
        };
        
        Database db = Database.getInstance();
        
        // read json
        try {
            db.initialize(prd_files);
        } catch (IOException ex) {
            Xbox.error(ex);
            // System.out.println("[Error] Fail to read json file!");
        }
        // set save path
        Xbox.setSaveFilePaths(prd_files);
        
        login_or_register.load();
    }
}
