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
public interface Command {
	// execute
	void execute(String[] cmdLine);
	
}
