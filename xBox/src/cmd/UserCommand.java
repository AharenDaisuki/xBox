package cmd;

import java.util.*;
import data.*;

public abstract class UserCommand implements Command{

    private static Client thisClient; 
    protected static ClientManager clientManager=ClientManager.getInstance();
	protected static RequestStorer requeststorer=RequestStorer.getInstance();
	protected static ClientSearcher clientSearcher=ClientSearcher.getInstance();

    public void execute(String[] cmdLine){
        
    }

    public String register(String username, String password,String phone_num){
        Client newClient=new ClientStaff(username,password,phone_num);
        clientManager.insert(newClient);
        return newClient.getName();
    }

    public void login(String username, String password){
        try{
        }
        catch(InputMismatchException e){
            System.out.println("Wrong Password");
        }
    }

}

