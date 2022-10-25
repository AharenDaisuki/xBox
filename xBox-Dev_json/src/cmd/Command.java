/**
 * 
 */
package cmd;

/**
 * @author xyli45
 * 
 * Description: 
 * command interface, extended by undoable.
 *
 */
import data.Client;
import ex.ExEntryNotFound;
import ex.ExNoSufficientRentable;

public interface Command {
	// execute
	String execute(String[] cmdLine, Client thisClient) throws ExNoSufficientRentable, ExEntryNotFound;
	
}
