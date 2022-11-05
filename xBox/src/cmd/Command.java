/**
 * 
 */
package cmd;

/**
 * @author lixiaoyang
 * 
 * @brief Description for Command.java
 * 
 * Command interface for specific command classes to implement, providing execute() function
 *
 */
import data.Client;
import ex.ExEntryNotFound;
import ex.ExNoSufficientRentable;

public interface Command {
	// execute
	String execute(String[] cmdLine, Client thisClient) throws ExNoSufficientRentable, ExEntryNotFound;
	
}
