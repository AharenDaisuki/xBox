package cmd;

import java.util.InputMismatchException;

import data.*;

@Deprecated
public class ClientRegisterCommand implements Command{
    @Override
    public void execute(String[] cmdLine, Client thisClient){
        //String username, String password,String Y/N
        ClientManager clientManager=ClientManager.getInstance();
        Client newClient;

        if(cmdLine[2].equals("y")) {
            //newClient=new ClientStaff(cmdLine[0],cmdLine[1]);
        }else {
            //newClient=new ClientStudent(cmdLine[0],cmdLine[1]);
        }
        //clientManager.insert(newClient);
    }

}
