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
import ex.ExNoSufficientRentable;

public interface Command {
	// execute
	void execute(String[] cmdLine, Client thisClient) throws ExNoSufficientRentable;
	
}
