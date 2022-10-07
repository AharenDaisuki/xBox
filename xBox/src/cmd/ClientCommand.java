package cmd;

import java.util.InputMismatchException;

import data.*;

public class ClientCommand extends UserCommand{
    protected static RentableManager rentableManager=RentableManager.getInstance() ;
    protected static RecordManager recordManager= RecordManager.getInstance();
    protected static RequestManager requestManager= RequestManager .getInstance();
     ClientManager clientManager=ClientManager.getInstance(); 
    protected static RequestStorer requeststorer=RequestStorer.getInstance();
    protected static ClientSearcher clientSearcher=ClientSearcher.getInstance();
    protected static RentableAllocator rentableAllocator=RentableAllocator.getInstance();

    public String register(String username, String password,String phone_num){
        //String username, String password,String phone_num
        Client newClient=new ClientStaff(username,password,phone_num);
        clientManager.insert(newClient);
        return newClient.getName();
    }
    @Override
    public void login(){
        //String username, String password,String phone_num
        try{
            if (findClient(username)){
                if (validLogin(password)){
                    return thisClient;
                }
            }
        }
        catch(InputMismatchException e){
            System.out.println("Wrong Password");
        }
    }
    public static boolean validLogin(String passwordIn){
        if(thisClient.getPassword().equals(passwordIn)){
            return true;
        }
        else return false;
    }
    public static boolean findClient(String usernameIn){
        for(int i=0;i<clientList.size();i++){
            if(clientList.get(i).getName().equals(usernameIn)){
                thisClient=clientList.get(i);
                return true;
            }
        }
        return false;
    }
}
