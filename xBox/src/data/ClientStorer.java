package data;

import java.util.ArrayList;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class ClientStorer{
	private ArrayList<Client> list;
    private static ClientStorer storer=new ClientStorer();
	
	private ClientStorer()
	{
		list=new ArrayList<>();
	}

	public static ClientStorer getInstance()
	{
		return storer;
	}
	public ArrayList<Client> getList()
	{
		return list;
	}
	
	public static Client getClientByJSONObject(JSONObject jsonObject)
	{
		Client c;
		if(jsonObject.get("type").toString().equals("staff"))
			c=new ClientStaff(jsonObject.get("email").toString(),
							jsonObject.get("phoneNo").toString(),
							jsonObject.get("password").toString());
		else if(jsonObject.get("type").toString().equals("student"))
			c=new ClientStudent(jsonObject.get("email").toString(),
					jsonObject.get("phoneNo").toString(),
					jsonObject.get("password").toString());
		else
			c=null;//exception needed
		return c;
	}

    public void readFromJson(String filePathName) throws IOException{
    	list=new ArrayList<>();
    	File file=new File(filePathName);
    	String jsonString=new String(Files.readAllBytes(Paths.get(file.getPath())));
    	JSONArray arr=new JSONArray(jsonString);
    	for(Object obj:arr)
    	{
    		JSONObject jsonObject=new JSONObject(obj.toString());
    		list.add(getClientByJSONObject(jsonObject));
    	}
    }

    public void writeToJson() {
    }
}
