package cmd;

import java.util.InputMismatchException;

public class ClientCommand extends UserCommand{
    protected static RentableManager rentableManager=RentableManager.getInstance() ;
    protected static RecordManager recordManager= RecordManager.getInstance;
    protected static RequestManager requestManager= RequestManager .getInstance();
    protected static clientManager clientManager=ClientManager.getInstance((); protected static Requeststorer requeststorer=Requeststorer.getInstance();
    protected static ClientSearcher clientSearcher=ClientSearcher.getInstance(();

    @Override
    public String register(String username, String password) {
        return super.register(username, password);
    }
    @Override
    public void login(String username, String password){
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
