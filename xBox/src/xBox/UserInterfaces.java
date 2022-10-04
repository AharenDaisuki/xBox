package xBox;

import java.util.ArrayList;
import data.Client;

public class UserInterfaces {
	private static UserInterfaces instance = new UserInterfaces();
	private static ArrayList<Client> allUsers; 
	
	private UserInterfaces() { allUsers = new ArrayList<>(); }
	
	public static UserInterfaces getInstance() {
		return instance;
	}
}
