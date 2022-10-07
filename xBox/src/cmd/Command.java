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
import data.*;
public interface Command {
	// execute
	void execute(String[] cmdLine);
	
}
