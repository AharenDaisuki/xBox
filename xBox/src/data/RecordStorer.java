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

public class RecordStorer implements XBoxStorer<Record>{
	private ArrayList<Record> recordList;
	private static RecordStorer storer = new RecordStorer();
	
	private RecordStorer(){
		recordList=new ArrayList<>();
	}

	public static RecordStorer getInstance(){
		return storer;
	}
	
	public ArrayList<Record> getList(){
		return recordList;
	}

    @Override
    public void addEntry(Record entry) {
        recordList.add(entry);
    }

    @Override
    public void delEntry(Record entry) {
        recordList.remove(entry);
    }

    public static Record getRecordByJSONObject(JSONObject jsonObject)
    {
        Client client;
        Rentable rentable;
        Date dueDate=new Date();
        boolean isPaid;
        client=ClientStorer.getClientByJSONObject(new JSONObject(jsonObject.get("client").toString()));
        rentable=RentableStorer.getRentableByJSONObject(new JSONObject(jsonObject.get("rentable").toString()));
        dueDate.setTime(jsonObject.getLong("dueDate"));
        isPaid=(jsonObject.getBoolean("isPaid"));
        return new Record(client,rentable,dueDate,isPaid);
    }

    public static JSONObject putRecordToJSONObject(Record r)
    {
        JSONObject jo=new JSONObject();
        jo.put("client", new JSONObject(r.getClient().toJSONString()));
        jo.put("rentable", new JSONObject(r.getRentable().toJSONString()));
        jo.put("dueDate", r.getDue().getTime());
        jo.put("isPaid", r.getPaymentStatus());
        return jo;
    }

    public void readFromJson(String filePathName) throws IOException {
        recordList=new ArrayList<>();
        File file = new File(filePathName);
        // File file = new File(System.getProperty("user.dir") + "/xBox/src/datasrc/RecordStorer.json");
        String jsonString=new String(Files.readAllBytes(Paths.get(file.getPath())));
        JSONArray arr=new JSONArray(jsonString);
        for(Object obj:arr)
        {
            JSONObject jsonObject=new JSONObject(obj.toString());
            recordList.add(getRecordByJSONObject(jsonObject));
        }
    }

    public void writeToJson(String filePathName) throws IOException{
        JSONArray arr=new JSONArray();
        for(Record r:recordList)
        {
            JSONObject jsonObject = new JSONObject();
            jsonObject=putRecordToJSONObject(r);
            arr.put(jsonObject);
        }
        File file = new File(filePathName);
        // File file = new File(System.getProperty("user.dir") + "/xBox/src/datasrc/RecordStorer.json");
        FileUtils.write(file, arr.toString(), "utf-8", false);
    }
}
