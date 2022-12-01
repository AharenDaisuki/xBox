package test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Date;

import org.junit.Test;

import data.*;
import data.Record;

import org.json.JSONArray;
import org.json.JSONObject;

public class TestRecordStorer {
	private RecordStorer rs=RecordStorer.getInstance();
	private Client c=new ClientStaff("123@abc.com","1234","123");
	private Rentable r=new Bag("001",new RentableStatusAvailable());
	private Date d=new Date(114514);
	
	private Record record=new Record(c,r,d,false);
	
	@Test
	public void test_01()
	{
		rs.addEntry(record);
		boolean res=rs.getList().contains(record);
		assertEquals(res,true);
	}
	
	@Test
	public void test_02()
	{
		rs.delEntry(record);
		boolean res=rs.getList().contains(record);
		assertEquals(res,false);
	}
	
	@Test
	public void test_03()
	{
		JSONObject jobj=new JSONObject();
		jobj.put("isPaid", false);
		jobj.put("rentable", new JSONObject(r.toJSONString()));
		jobj.put("dueDate", d.getTime());
		jobj.put("client", new JSONObject(c.toJSONString()));
		Record r=RecordStorer.getRecordByJSONObject(jobj);
		assertEquals(r.toString(),record.toString());
	}
	
	@Test
	public void test_04()
	{
		JSONObject _jobj=RecordStorer.putRecordToJSONObject(record);
		JSONObject jobj=new JSONObject();
		jobj.put("isPaid", false);
		jobj.put("rentable", new JSONObject(r.toJSONString()));
		jobj.put("dueDate", d.getTime());
		jobj.put("client", new JSONObject(c.toJSONString()));
		assertEquals(_jobj.toString(),jobj.toString());
	}
	
	@Test
	public void test_05() throws IOException
	{
		rs.readFromJson("src/test/testRecordJSON.json");
		assertEquals(rs.getList().get(0).toString(),record.toString());
	}
	
	@Test
	public void test_06() throws IOException
	{
		rs.writeToJson("src/test/testRecordJSON.json");;
		rs.readFromJson("src/test/testRecordJSON.json");
		assertEquals(rs.getList().get(0).toString(),record.toString());
	}
}
