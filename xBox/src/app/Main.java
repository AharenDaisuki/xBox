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
 * @file Main.java
 * 
 * This file defines the main class of the project, which is the very start to run the program.
 * 
 */

public class Main {
    public static void main(String[] args) {
        // System.out.println(System.getProperty("user.dir"));
        login_or_register.load();
    }
}
