package cmd;

import java.util.ArrayList;
import java.util.Arrays;
import utils.*;
import data.*;

public class ClientStoreBoxCommand extends Undoable{
    private Client myClient;
    @Override
    public void redo(){      
        addUndo(this);
        
    }
    @Override
    public void undo(){

        addRedo(this);
    }

    public void execute(String[] cmdLine,Client thisClient){
        /*
        [3*num+1] number Of Request（num）,num*rentableId,num*yes or not used,month
        */
        RequestSearcher requestSearcher=RequestSearcher.getInstance();

        int num=Integer.parseInt(cmdLine[0]);
        /* TODO: modify
        for (int i=1;i<=num;i++){
            Request request= requestSearcher.searchByRentableID(cmdLine[i]);
            //Rentable rentable=request.getRentable();
            if(cmdLine[num+i].equals("_")){
                request.setTarget(new RequestButNotUsed());
            }
            else if(cmdLine[num+i].equals("y")){
                Day curreDay= SystemDate.getInstance();
                int rentMonth=Integer.parseInt(cmdLine[cmdLine.length-1]);
                Day dueDay=new Day(curreDay.getYear(),rentMonth+curreDay.getMonth(),curreDay.getDay());
                request.setTarget(new RequestAndUse(dueDay,thisClient));
            }
        }*/
        addUndo(this);
        clearList();
    }
	
}