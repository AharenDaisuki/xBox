/**
 * 
 */
package cmd;

import java.util.ArrayList;

import debug.DebugConfig;
import ex.*;
import data.*;
/**
 * @author lixiaoyang
 *
 * @brief Description for Undoable.java
 * 
 * This is the abstract class for specific command class to extend, implementing Command interface.
 * All undoable commands are supposed to extend this class which provides undo and redo functions. 
 *  
 * 
 */
public abstract class Undoable implements Command {
	// undo & redo method
	public abstract String undo();
	public abstract String redo();
	
	// undo list & redo list // TODO
	private static ArrayList<Undoable> undoList = new ArrayList<>(); /// <This is the undo list to store command objects to undo 
	private static ArrayList<Undoable> redoList = new ArrayList<>(); /// <This is the redo list to store command objects to redo

	// modify list (subclass-visible)
	protected static void addUndo(Undoable cmd) {
		undoList.add(cmd);
	}
	
	protected static void addRedo(Undoable cmd) {
		redoList.add(cmd);
	}
	
	public static void clearList() {
		redoList.clear();
	}
	
	// fetch undoable and execute undo or redo function
	
	/**
	 * @brief undo command
	 * 
	 *  static undo command function for Undoable class 
	 *  
	 * @return string, log to be output
	 */
	public static String undoCmd() throws ExEmptyVector {
		// try {
		//	if(undoList.isEmpty()) {
		//		throw new ExEmptyVector("[Error] Nothing to undo");
		//	}
			// undo from the top of the stack
		//	return undoList.remove(undoList.size()-1).undo();
		//} catch(ExEmptyVector ex) {
		//	if(DebugConfig.CMD_UNDOABLE_DEBUG_FLAG) {
		//		System.out.println(ex.getMessage());
		//	}
		//}
        if(undoList.isEmpty()) {
            throw new ExEmptyVector("[Error] Nothing to undo!");
        }
        return undoList.remove(undoList.size()-1).undo();
		// return null;
	}
	
	/**
     * @brief redo command
     * 
     *  static redo command function for Undoable class 
     *  
     * @return string, log to be output
     */
	public static String redoCmd() throws ExEmptyVector {
		//try {
		//	if(redoList.isEmpty()) {
		//		throw new ExEmptyVector("[dev Error] empty vector => {redoCmd}");
		//	}
			// redo from the top of the stack
		//	return redoList.remove(redoList.size()-1).redo();
		//} catch(ExEmptyVector ex) {
		//	if(DebugConfig.CMD_UNDOABLE_DEBUG_FLAG) {
		//		System.out.println(ex.getMessage());
		//	}
		//}
        if(redoList.isEmpty()) {
            throw new ExEmptyVector("[Error] Nothing to redo!");
        }
        // redo from the top of the stack
        return redoList.remove(redoList.size()-1).redo();
		//return null;
	}
}
