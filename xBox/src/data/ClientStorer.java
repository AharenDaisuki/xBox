package data;

import org.apache.commons.io.FileUtils;
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

	public static JSONObject putClientToJSONObject(Client c)
	{
		JSONObject jo=new JSONObject();
		if(c instanceof ClientStaff){
		 	jo.put("email", c.getEmail());
			jo.put("phoneNo", c.getPhoneNo());
			jo.put("password", c.getPassword());
			jo.put("type","staff");
		}
		else if(c instanceof ClientStudent){
			jo.put("email", c.getEmail());
			jo.put("phoneNo", c.getPhoneNo());
			jo.put("password", c.getPassword());
			jo.put("type","student");
		}
		else
			jo=null;//exception needed
		return jo;
	}

    public void readFromJson() throws IOException{
    	list=new ArrayList<>();
    	File file = new File(System.getProperty("user.dir") + "/src/datasrc/ClientStorer.json");
    	String jsonString=new String(Files.readAllBytes(Paths.get(file.getPath())));
    	JSONArray arr=new JSONArray(jsonString);
    	for(Object obj:arr)
    	{
    		JSONObject jsonObject=new JSONObject(obj.toString());
    		list.add(getClientByJSONObject(jsonObject));
    	}
    }

    public void writeToJson() throws IOException{
		JSONArray arr=new JSONArray();
		for(Client c:list)
		{
			JSONObject jsonObject = new JSONObject();
			jsonObject=putClientToJSONObject(c);
			arr.put(jsonObject);
		}
		File file = new File(System.getProperty("user.dir") + "/src/datasrc/ClientStorer.json");
		FileUtils.write(file, arr.toString(), "utf-8", false);
    }
}
