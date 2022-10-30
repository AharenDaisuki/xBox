package data;

import java.util.HashMap;
import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;

public class RentableStorer implements XBoxStorer<Rentable>{
    private static RentableStorer instance = new RentableStorer();
    
    private HashMap<String, ArrayList<Rentable>> manager;

    private RentableStorer(){
        manager = new HashMap<>();
        // TODO: hard coding => rentable type
        manager.put("BOX", new ArrayList<Rentable>());
        manager.put("BAG", new ArrayList<Rentable>());
        // TODO: add new rentable type
    }

    public static RentableStorer getInstance(){
        return instance;
    }

    public HashMap<String,ArrayList<Rentable>> getManager(){
        return manager;
    }
    
    public ArrayList<Rentable> getList(String rentableType){
        return manager.get(rentableType);
    }

    @Override
    public void addEntry(Rentable entry) {
        String type = entry.getType();
        if(manager.get(type) == null) {
            manager.put(type, new ArrayList<>());
        }
        manager.get(entry.getType()).add(entry);
    }

    @Override
    public void delEntry(Rentable entry) {
        String type = entry.getType();
        // if(manager.get(type) == null) {
            // TODO: exception
        // }
        manager.get(entry.getType()).remove(entry);
    }
    
    public static Rentable getRentableByJSONObject(JSONObject jsonObject)
    {
		Rentable r;
		RentableStatus status;
		String statusJSONString=jsonObject.get("status").toString();
		JSONObject statusJSONObject=new JSONObject(statusJSONString);
		if(statusJSONObject.get("status").toString().equals("Available"))
			status=new RentableStatusAvailable();
		else if(statusJSONObject.get("status").toString().equals("Occupied"))
		{
			String clientString=statusJSONObject.get("client").toString();
			long dueNum=statusJSONObject.getBigInteger("due").longValue();
			JSONObject clientJSONObject=new JSONObject(clientString);
			Client client=ClientStorer.getClientByJSONObject(clientJSONObject);
			Date due=new Date(dueNum);
			status=new RentableStatusOccupied(due,client);
		}
		else if(statusJSONObject.get("status").toString().equals("Requested"))
		{
			String clientString=statusJSONObject.get("client").toString();
			JSONObject clientJSONObject=new JSONObject(clientString);
			Client client=ClientStorer.getClientByJSONObject(clientJSONObject);
			status=new RentableStatusRequested(client);
		}
		else if(statusJSONObject.get("status").toString().equals("Pending"))
		{
			String clientString=statusJSONObject.get("client").toString();
			JSONObject clientJSONObject=new JSONObject(clientString);
			Client client=ClientStorer.getClientByJSONObject(clientJSONObject);
			status=new RentableStatusPending(client);
		}
		else status=null; //Exception needed
		if(jsonObject.get("type").toString().equals("BAG"))
			r=new Bag(jsonObject.get("id").toString().substring(3),status);
		else if(jsonObject.get("type").toString().equals("BOX"))
			r=new Box(jsonObject.get("id").toString().substring(3),status);
		else
			r=null; //Exception needed
		return r;
    }

	public static JSONObject putRentableToJSONObject(Rentable r)
	{
		JSONObject jo=new JSONObject();
		RentableStatus status;
		if(r.getStatus().equals("Available")){
		 	jo.put("id", r.getId());
			jo.put("status", new RentableStatusAvailable());
			jo.put("type", r.getType());
		}
		else if(r.getStatus().equals("Occupied")){
			jo.put("id", r.getId());
			jo.put("status", r.getStatus());
			jo.put("type", r.getType());
		}
		else if(r.getStatus().equals("Requested")){
			jo.put("id", r.getId());
			jo.put("status", r.getStatus());
			jo.put("type", r.getType());
		}
		else if(r.getStatus().equals("Pending")){
			jo.put("id", r.getId());
			jo.put("status", r.getStatus());
			jo.put("type", r.getType());
		}
		else
			jo=null;//exception needed
		return jo;
	}

    public void readFromJson() throws IOException{
    	manager=new HashMap<>();
        manager.put("BOX", new ArrayList<Rentable>());
        manager.put("BAG", new ArrayList<Rentable>());
        File file = new File(System.getProperty("user.dir") + "/src/datasrc/RentableStorer.json");
    	String jsonString=new String(Files.readAllBytes(Paths.get(file.getPath())));
    	JSONArray arr=new JSONArray(jsonString);
    	for(Object obj:arr)
    	{
    		JSONObject jsonObject=new JSONObject(obj.toString());
    		Rentable r=getRentableByJSONObject(jsonObject);
    		manager.get(r.getType()).add(r);
    	}
    }

	public void writeToJson() throws IOException{
		JSONArray arr=new JSONArray();
		for(ArrayList<Rentable> Rarr:manager.values())
		{
			for (Rentable r:Rarr)
			{
				JSONObject jsonObject = new JSONObject();
				jsonObject=putRentableToJSONObject(r);
				arr.put(jsonObject);
			}
		}
		File file = new File(System.getProperty("user.dir") + "/src/datasrc/RentableStorer.json");
		FileUtils.write(file, arr.toString(), "utf-8", false);
    }
}
