package cmd;

import java.util.ArrayList;
import data.*;

public class GetBoxCommand extends Undoable{

    @Override
    public void redo(){
        addUndo(this);
        
    }
    @Override
    public void undo(){

        addRedo(this);
    }

    public void execute(String[] cmdLine){
        /*
        cmdLine[0] 
        request,box/bag,number,date
        */

        rentableManager.lendOutRentable(rentable);
        RecordManager.insert(thisClient,rentable,duedate); 
        
    };
}
