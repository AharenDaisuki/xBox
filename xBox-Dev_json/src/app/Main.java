/**
 * 
 */
package app;

import io.login_or_register;
// import Xbox package
import xBox.*;

/**
 * @author lixiaoyang
 * Description: Main class of the project
 * 
 */

public class Main {
    public static void main(String[] args) {
        Xbox system = Xbox.getInstance();
        login_or_register.load();
    }
}
