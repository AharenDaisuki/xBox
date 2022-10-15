/**
 * 
 */
package cmd;

import java.util.ArrayList;

import debug.DebugConfig;
import ex.*;
import data.*;
/**
 * @author xyli45
 *
 * Description: 
 * abstract class for undoable class to extend, implementing Command interface.
 *  
 */
public abstract class Undoable implements Command {
	// undo & redo method
	public abstract String undo();
	public abstract String redo();
	
	// undo list & redo list
	private static ArrayList<Undoable> undoList = new ArrayList<>();
	private static ArrayList<Undoable> redoList = new ArrayList<>();

	// modify list (subclass-visible)
	protected static void addUndo(Undoable cmd) {
		undoList.add(cmd);
	}
	
	protected static void addRedo(Undoable cmd) {
		redoList.add(cmd);
	}
	
	protected static void clearList() {
		redoList.clear();
	}
	
	// fetch undoable and execute undo or redo function
	public static String undoCmd() {
		try {
			if(undoList.isEmpty()) {
				throw new ExEmptyVector("[Error] Nothing to undo");
			}
			// undo from the top of the stack
			return undoList.remove(undoList.size()-1).undo();
		} catch(ExEmptyVector ex) {
			if(DebugConfig.CMD_UNDOABLE_DEBUG_FLAG) {
				System.out.println(ex.getMessage());
			}
		}
		return null;
	}
	
	public static String redoCmd() {
		try {
			if(redoList.isEmpty()) {
				throw new ExEmptyVector("[dev Error] empty vector => {redoCmd}");
			}
			// redo from the top of the stack
			return redoList.remove(redoList.size()-1).redo();
		} catch(ExEmptyVector ex) {
			if(DebugConfig.CMD_UNDOABLE_DEBUG_FLAG) {
				System.out.println(ex.getMessage());
			}
		}
		return null;
	}
}
