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
        // file path names
        // String userDir = System.getProperty("user.dir");
        // System.out.println(System.getProperty("user.dir"));
        // String[] tmpFilePathNames = {
        //        userDir + "/src/datasrc/available_items.json",
        //        userDir + "/src/datasrc/ClientStorer.json",
        //        userDir + "/src/datasrc/RequestStorer.json",
        //        userDir + "/src/datasrc/RecordStorer.json"
        //};
        // main class
        //Xbox system = Xbox.getInstance();
        // data base
        //Database database = Database.getInstance();
        // initialize
        //try {
        //    database.initialize();
        //} catch (IOException ex) {
        //    System.out.println("[Error] Fail to read json file!");
        //} 
        // io
        login_or_register.load();
    }
}
