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

public interface Command {
	// execute
	void execute(String[] cmdLine, Client thisClient);
	
}
