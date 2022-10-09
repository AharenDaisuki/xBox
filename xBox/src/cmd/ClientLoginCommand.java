package cmd;

import data.*;
public class ClientLoginCommand implements UserCommand{
    @Override
    public void execute(String[] cmdLine) {
        //String username, String password,String phone_num
        ClientSearcher clientSearcher=ClientSearcher.getInstance();
        Client thisClient=clientSearcher.searchByClientEmail(cmdLine[0]);
        try{
            if (thisClient.getPassword().equals(cmdLine[1])){
                //return thisClient;
            }
        }
        catch(Exception e){
            System.out.println("Wrong Password");
        }
    }    
}
