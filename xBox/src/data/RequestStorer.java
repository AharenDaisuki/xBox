package data;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class RequestStorer implements XBoxStorer<Request>{
    // data
    private ArrayList<Request> requestList;
    //private static int distributedId = 0;
    
    // singleton 
	private static RequestStorer storer = new RequestStorer();
	
	private RequestStorer(){
		requestList = new ArrayList<>();
	}

	public static RequestStorer getInstance(){
		return storer;
	}
	
	/*
	public int generateRequestId() {
	    return distributedId++;
	}*/
	
	// TODO: replace
	public ArrayList<Request> getList(){
		return requestList;
	}

    @Override
    public void addEntry(Request request_) {
        requestList.add(request_);
    }

    @Override
    public void delEntry(Request request_) {
        requestList.remove(request_);
    }
    
    public static Request getRequestByJSONObject(JSONObject jsonObject)
	{
		Client client;
		Rentable rentable;
		Date dueDate=new Date();
		client=ClientStorer.getClientByJSONObject(new JSONObject(jsonObject.get("client").toString()));
		rentable=RentableStorer.getRentableByJSONObject(new JSONObject(jsonObject.get("rentable").toString()));
		dueDate.setTime(Integer.parseInt(jsonObject.get("dueDate").toString()));
		return new Request(client,rentable,dueDate);
	}

	public static JSONObject putRequestToJSONObject(Request r)
	{
		JSONObject jo=new JSONObject();
		jo.put("client", r.getClient());
		jo.put("rentable", r.getRentable());
		jo.put("dueDate", r.getDue());
		return jo;
	}

    public void readFromJson() throws IOException {
    	requestList=new ArrayList<>();
    	File file = new File(System.getProperty("user.dir") + "/src/datasrc/RequestStorer.json");
    	String jsonString=new String(Files.readAllBytes(Paths.get(file.getPath())));
    	JSONArray arr=new JSONArray(jsonString);
    	for(Object obj:arr)
    	{
    		JSONObject jsonObject=new JSONObject(obj.toString());
    		requestList.add(getRequestByJSONObject(jsonObject));
    	}
    }
	public void writeToJson() throws IOException{
		JSONArray arr=new JSONArray();
		for(Request r:requestList)
		{
			JSONObject jsonObject = new JSONObject();
			jsonObject=putRequestToJSONObject(r);
			arr.put(jsonObject);
		}
	    File file = new File(System.getProperty("user.dir") + "/src/datasrc/RequestStorer.json","w");
		FileUtils.write(file, arr.toString(), "utf-8", false);
	}	
}
