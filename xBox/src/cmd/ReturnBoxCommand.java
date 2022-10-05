package cmd;

import java.util.ArrayList;
import data.*;

public class ReturnBoxCommand extends Undoable{

    public void redo(){}
    public void undo(){}

    public void execute(String[] cmdLine){
        rentableManager.getBackRentable();
        recordManager.delete();  
        RecordManager.insert();    
    }
}
