package cmd;

import java.util.*;
import data.*;

public abstract class UserCommand implements Command{

    private static Client thisClient; 
    private static ArrayList<Client> clientList=new ArrayList<Client>();

    public void execute(String[] cmdLine){
        
    }

    public String register(String username, String password){
        Client newClient=new Client(username,password);
        clientList.add(newClient);
        return thisClient.getName();
    }

    public void login(String username, String password){
        try{
            thisClient.changeStatus();
        }
        catch(InputMismatchException e){
            System.out.println("Wrong Password");
        }
    }

}

