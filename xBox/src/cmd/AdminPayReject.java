package cmd;

import java.util.ArrayList;

import data.*;
public class AdminPayReject extends Undoable{
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
         *clientName
        */

    }
}
