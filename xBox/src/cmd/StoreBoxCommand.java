package cmd;

import java.util.ArrayList;
import data.*;

public class StoreBoxCommand extends Undoable{


    @Override
    public void redo(){
        undoList.add(null);
    }
    @Override
    public void undo(){

    }

    @Override
    public void execute(String[] cmdLine){
        Client thisClient=.equals();
        
        thisClient.borrowItem(new );
        insert();
        cmdLine[0]
    };
	
}