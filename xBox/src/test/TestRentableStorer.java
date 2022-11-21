package test;

import data.*;
import org.junit.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONObject;


public class TestRentableStorer {
    
    RentableStorer rs = RentableStorer.getInstance();
    Rentable box1 = new Box("0001", new RentableStatusAvailable());
    Rentable box2 = new Box("0002", new RentableStatusAvailable());
    
    @Test
    public void test_01() {
        rs.addEntry(box1);
        boolean res = rs.getManager().get("BOX").contains(box1);
        assertEquals(true,res);
    }
    
    @Test
    public void test_02() {

        Rentable box1 = new Box("0002", new RentableStatusAvailable());
        rs.addEntry(box2);
        boolean res = rs.getList("BOX").contains(box2);
        assertEquals(true,res);
    }
    
    @Test
    public void test_03() {
        rs.delEntry(box1);
        boolean res = rs.getManager().containsValue(box1);
        assertEquals(false,res);
    }

    public void test_04() {
        JSONObject jobj=new JSONObject();
        jobj.put("id","001");
        jobj.put("type", "BAG");
        JSONObject jobj_status=new JSONObject();
        jobj_status.put("status", "Available");
        jobj.put("status", jobj_status);
        Rentable r=new Bag("001",new RentableStatusAvailable());
        Rentable r_=RentableStorer.getRentableByJSONObject(jobj);
        assertEquals(r.toString(),r_.toString());
    }
    
    @Test
    public void test_05() {
        JSONObject jobj=new JSONObject();
        jobj.put("id","001");
        jobj.put("type", "BOX");
        JSONObject jobj_status=new JSONObject();
        jobj_status.put("status", "Occupied");
        Client c=new ClientStudent("abc@123.com","1234","12345");
        Date d=new Date((long)114514);
        jobj_status.put("client", new JSONObject(c.toJSONString()));
        jobj_status.put("due", d.getTime());
        jobj.put("status", jobj_status);
        Rentable r=new Box("001",new RentableStatusOccupied(d,c));
        Rentable r_=RentableStorer.getRentableByJSONObject(jobj);
        assertEquals(r.toString(),r_.toString());
    }
    
    @Test
    public void test_06() {
        JSONObject jobj=new JSONObject();
        jobj.put("id","001");
        jobj.put("type", "BOX");
        JSONObject jobj_status=new JSONObject();
        jobj_status.put("status", "Requested");
        Client c=new ClientStudent("abc@123.com","1234","12345");
        jobj_status.put("client", new JSONObject(c.toJSONString()));
        jobj.put("status", jobj_status);
        Rentable r=new Box("001",new RentableStatusRequested(c));
        Rentable r_=RentableStorer.getRentableByJSONObject(jobj);
        assertEquals(r.toString(),r_.toString());
    }
    
    @Test
    public void test_07() {
        JSONObject jobj=new JSONObject();
        jobj.put("id","001");
        jobj.put("type", "BOX");
        JSONObject jobj_status=new JSONObject();
        jobj_status.put("status", "Pending");
        Client c=new ClientStudent("abc@123.com","1234","12345");
        jobj_status.put("client", new JSONObject(c.toJSONString()));
        jobj.put("status", jobj_status);
        Rentable r=new Box("001",new RentableStatusPending(c));
        Rentable r_=RentableStorer.getRentableByJSONObject(jobj);
        assertEquals(r.toString(),r_.toString());
    }
    
    @Test
    public void test_08() {
        JSONObject jobj=new JSONObject();
        jobj.put("id","001");
        jobj.put("type", "BOX");
        JSONObject jobj_status=new JSONObject();
        jobj_status.put("status", "e");
        jobj.put("status",jobj_status);
        Rentable r_=RentableStorer.getRentableByJSONObject(jobj);
        assertEquals(null,r_);
    }
    
    @Test
    public void test_09() {
        JSONObject jobj=new JSONObject();
        jobj.put("id","001");
        jobj.put("type", "BOG");
        JSONObject jobj_status=new JSONObject();
        jobj_status.put("status", "Available");
        jobj.put("status",jobj_status);
        Rentable r_=RentableStorer.getRentableByJSONObject(jobj);
        assertEquals(null,r_);
    }
    
    @Test
    public void test_10() {
    	JSONObject jobj=RentableStorer.putRentableToJSONObject(box1);
    	JSONObject _jobj=new JSONObject(box1.toJSONString());
    	assertEquals(jobj.toString(),_jobj.toString());
    }
    
    @Test
    public void test_11() throws IOException {
    	rs.readFromJson("test/testRentableJSON.json");
    	assertEquals(rs.getList("BAG").get(0).toJSONString(),"{\"id\":\"001\",\"status\":{\"status\":\"Available\"},\"type\":\"BAG\"}");
    }
    
    @Test
    public void test_12() throws IOException {
    	rs.writeToJson("test/testRentableJSON.json");
    	rs.readFromJson("test/testRentableJSON.json");
    	assertEquals(rs.getList("BAG").get(0).toJSONString(),"{\"id\":\"001\",\"status\":{\"status\":\"Available\"},\"type\":\"BAG\"}");
    }
    
    @Test
    public void test_13()
    {
    	class RentableTemp extends Rentable
    	{

			public RentableTemp(String rentableID_, RentableStatus status_) {
				super(rentableID_, status_);
			}

			@Override
			public String getType() {
				return "TEMP";
			}

			@Override
			public double getPrice() {
				// TODO Auto-generated method stub
				return 0;
			}
    		
    	}
    	Rentable r3=new RentableTemp("001",new RentableStatusAvailable());
    	r3.getPrice();
        rs.addEntry(r3);
        boolean res = rs.getManager().get("TEMP").contains(r3);
        assertEquals(true,res);
    }
}
