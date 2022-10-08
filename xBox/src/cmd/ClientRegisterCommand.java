package cmd;

import java.util.InputMismatchException;

import data.*;

public class ClientRegisterCommand implements Command{
    @Override
    public void execute(String[] cmdLine, Client thisClient){
        //String username, String password,String Y/N
        ClientManager clientManager=ClientManager.getInstance();
        if(cmdLine[2].equals("y")) {
            Client newClient=new ClientStaff(cmdLine[0],cmdLine[1]);
        }
        else {
            Client newClient=new ClientStudent(cmdLine[0],cmdLine[1]);}
        clientManager.insert(newClient);
    }

}
