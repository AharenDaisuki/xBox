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
	public abstract void undo();
	public abstract void redo();
	
	// undo list & redo list
	private static ArrayList<Undoable> undoList = new ArrayList<>();
	private static ArrayList<Undoable> redoList = new ArrayList<>();
	
	protected static RentableManager rentableManager=RentableManager.getInstance();
	protected static RecordManager recordManager= RecordManager.getInstance();
	protected static RequestManager requestManager= RequestManager .getInstance(); 
	protected static ClientManager clientManager=ClientManager.getInstance();
	protected static RequestStorer requeststorer=RequestStorer.getInstance();
	protected static ClientSearcher clientSearcher=ClientSearcher.getInstance();
	protected static RentableAllocator rentableAllocator=RentableAllocator.getInstance();

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
	public static void undoCmd() {
		try {
			if(undoList.isEmpty()) {
				throw new ExEmptyVector("[dev Error] empty vector => {undoCmd}");
			}
			// undo from the top of the stack
			undoList.remove(undoList.size()-1).undo();
		} catch(ExEmptyVector ex) {
			if(DebugConfig.CMD_UNDOABLE_DEBUG_FLAG) {
				System.out.println(ex.getMessage());
			}
		}
	}
	
	public static void redoCmd() {
		try {
			if(redoList.isEmpty()) {
				throw new ExEmptyVector("[dev Error] empty vector => {redoCmd}");
			}
			// redo from the top of the stack
			redoList.remove(redoList.size()-1).redo();
		} catch(ExEmptyVector ex) {
			if(DebugConfig.CMD_UNDOABLE_DEBUG_FLAG) {
				System.out.println(ex.getMessage());
			}
		}
	}
}
