
package app;
import java.util.*;
import javax.swing.JOptionPane;
import javax.swing.text.Style;

import xBox.Xbox;

public class Main {

	static Xbox Database = Xbox.GetSystem();
	public static void quit(Scanner in){
		in.close();
		System.out.println("Thanks for using our product :)");

		System.exit(0);
	}
	public static String split_cmd(String cmd, String option){
		String[] buff = cmd.split(option);
//		System.out.println(buff[0]);
		if(buff.length ==1)
			return null;
		buff = buff[1].split(" -");
		
		return buff[0].trim();
	}
	public static int login(Scanner in){
		int userid=-1;
		while(true){
			System.out.println("All the parameters for the command are require for [] and are optional for <>, the order do not matter.");
			System.out.println("For login in: login [-u = username] [-p = password]");
			System.out.println("For register new account: register [-u = username] [-p = password] [-n = phone_number]");
			System.out.println("For quit the program: quit");
			
			String cmd_line = in.nextLine();
			String cmd_type = cmd_line.split(" ")[0];
//			System.out.println(cmd_type);
			if(cmd_type.equals("register")){
				String username = split_cmd(cmd_line,"-u");
				String password = split_cmd(cmd_line,"-p");
				int phone_number = Integer.parseInt(split_cmd(cmd_line,"-n"));
//				userid = regitster(username,password);
				userid = 1;
			}else if(cmd_type.equals("login")){
				String username = split_cmd(cmd_line,"-u");
				String password = split_cmd(cmd_line,"-p");
				//userid = login_in(username,password);
				userid=1;

			}else if(cmd_type.equals("quit")){
				quit(in);
			}else{
				System.out.println("Unkown Command, Please retry");
			}
			if(userid != -1) {
				break;
			}
		}
		return userid; //userid
	}
	public static ArrayList<Integer> FindArray(String array) {
		String[] array_string = array.split(",");
		ArrayList<Integer> array_int = new ArrayList<Integer>();
		for(int i=0;i<array_string.length;i++) {
			array_int.add(Integer.parseInt(array_string[i]));
		}
		return array_int;
	}
	public static ArrayList<Integer> getArrayNumb(String array) {
		String[] array_string = array.split(",");
		ArrayList<Integer> array_numb = new ArrayList<Integer>();

		for(int i=0;i<array_string.length;i++) {
			array_numb.add(Integer.parseInt (array_string[i].split(",")[0].split("(")[0]));
		}
		return array_numb;
	}
	
	public static ArrayList<String> getArrayType(String array) {
		String[] array_string = array.split(",");
		
		ArrayList<String> array_type = new ArrayList<String>();

		for(int i=0;i<array_string.length;i++) {
			array_type.add(array_string[i].split(",")[1].split(")")[0]);
		}
		return array_type;
	}
	
	public static void working(int userid,Scanner in) {
		String username = new String("djj"); // username = findUser(userid);
		System.out.println("Hello "+username);
		System.out.println("All the parameters for the command are require for [] and are optional for <>, the order do not matter.");
		System.out.println("For find boxes: find <-b = boxid1,boxid2...  (default is all)>");
		System.out.println("For store boxes: store [ -b= (number,box_type),(number,box_type)... ]"); 
		System.out.println("For get boxes: get [-b = boxid1,boxid2 ] [ -a=address ]");
		System.out.println("For undo: undo");
		System.out.println("For redo: redo");


//		System.out.println()
		System.out.println("For quit the program: quit");
		
		String cmd_line = in.nextLine();
		String cmd_type = cmd_line.split(" ")[0];
		
		if(cmd_type.equals("find")){
			String b = split_cmd(cmd_line,"-b");
			ArrayList<Integer> boxids; 
			if(b!=null) {
				boxids=FindArray(b);
			}
			//find(userid,b,boxids);
			
		}else if(cmd_type.equals("store")){
			String b =split_cmd(cmd_line,"-b");
			ArrayList<Integer> box_numbers; 
			ArrayList<String> box_types; 

			box_numbers=getArrayNumb(b);
			box_types=getArrayType(b);
			//store(userid,box_numbers,box_types);

		}else if(cmd_type.equals("get")) {
			String b =split_cmd(cmd_line,"-b");
			ArrayList<Integer> boxids; 
			boxids=FindArray(b);
			String address = split_cmd(cmd_line,"-a");
			//get(userid,boxids,address);
		}
		else if(cmd_type.equals("quit")){
			quit(in);
		}else{
			System.out.println("Unkown Command, Please retry");
		}

	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		int userid = login(in);
		working(userid,in);
		quit(in);
		
	}




	// public static String login(Scanner in) {
	// 	while(true){

	// 		System.out.println("Please Enter Your User Name\n  or type -r for register \n  -q for quit");
	// 		String cmd = in.nextLine();

	// 		if(cmd.equals("-q")){
	// 			return "quit";
	// 		}else if(cmd.equals("")||cmd.equals(" ")){
	// 			continue;
	// 		}else if(cmd.equals("-r")){
	// 			while(true){

	// 				System.out.println("Enter User Name for register  or type -q for quit");
	// 				cmd = in.nextLine();
	// 				if(cmd.equals("-q")){
	// 					break;
	// 				}
	// 				if(Database.GetUserName(cmd)==-1){
	// 					String UserName = cmd;
	// 					while(true){
	// 						System.out.println("Please Enter the password or type -q for quit");
	// 						String password1 = in.nextLine();
	// 						if(password1.equals("-q")){
	// 							break;
	// 						}
	// 						System.out.println("Please Enter same the password");
	// 						String password2 = in.nextLine();
	// 						if(password1.equals(password2)){
	// 							Database.AddNewUser(UserName,password1);
	// 							return UserName;
	// 						}else{
	// 							System.out.println("Two password is not same. Please retry");
	// 						}
	// 					}

	// 				}else{
	// 					System.out.println("The User Name has been using by other users. Please retry.");
	// 				}

	// 			}
	// 		}
	// 		else{
	// 			if(Database.GetUserName(cmd)!=-1){
	// 				String UserName= cmd;
	// 				while(true){
	// 					System.out.println("Please Enter Password for:"+UserName+" or type -q for quit");
	// 					cmd = in.nextLine();
	// 					if(cmd.equals("-q")){
	// 						break;
	// 					}
	// 					if(Database.CheckUser(UserName,cmd)){
	// 						return UserName;

	// 					}else{
	// 						System.out.println("Wrong password for"+UserName+".");
	// 					}
	// 				}
	// 			}else{
	// 				System.out.println("InValid User Name.");
	// 			}
	// 		}

	// 	}
	// }
	
// 	public static void main(String[] args) {
// 		// System.out.println(split_cmd("register -username lxy -password 123","-username"));
// 		// System.out.println(split_cmd("register -username lxy -password 123","-password"));

// 		// TODO Auto-generated method stub
// 		// String Userguide = new String("Enter -q or quit for quit the app.\n");
// 		// Scanner in = new Scanner(System.in);
// 		// String UserName = login(in);
// 		// if(UserName=="-q"){
// 		// 	return;
// 		// }
// 		// while(true) {
// 		// 	System.out.println(Userguide);
// 		// 	String cmd = in.nextLine();
// 		// 	System.out.println(cmd);

// 		// 	if(cmd.equals("-q")) {
// 		// 		break;
// 		// 	}else if(cmd.equals("-d") || cmd.equals("djj")) {
// 		// 		System.out.println("Djj will pray for you");
// 		// 	}
			
// 		// }
// 		// System.out.println("Thanks for using our product :)");
// 		// in.close();
		
// 		// JOptionPane.showMessageDialog(null, "lxy yyds !");
// //		  //弹出确认对话框
// //		  int  option=JOptionPane.showConfirmDialog(null, "1+1=2?");
// //		  if(option==JOptionPane.YES_OPTION)
// //		   System.out.println("你选择的是Yes");
// //		  else
// //		   System.out.println("你选择的是No");
// //		  String name=JOptionPane.showInputDialog("请输入你的名字：");
// //		  int  age =Integer.parseInt(JOptionPane.showInputDialog("请输入你的年龄："));
// //		  JOptionPane.showMessageDialog(null, "你好，"+name+"\n你今年"+age+"岁了");
// 	}

}
