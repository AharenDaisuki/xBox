package test;

import static org.junit.Assert.assertEquals;

import org.json.JSONObject;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;

import data.*;
import data.Record;

public class TestRequestStorer {
	private RequestStorer rs=RequestStorer.getInstance();
	private Client c=new ClientStaff("123@abc.com","1234","123");
	private Rentable r=new Bag("001",new RentableStatusAvailable());
	private Date d=new Date(114514);
	
	private Request request=new Request(c,r,d);

	@Test
	public void test_01()
	{
		rs.addEntry(request);
		assertEquals(rs.getList().contains(request),true);
	}
	
	@Test
	public void test_02()
	{
		rs.delEntry(request);
		assertEquals(rs.getList().contains(request),false);
	}
	
	@Test
	public void test_03()
	{
		JSONObject jobj=new JSONObject();
		jobj.put("rentable", new JSONObject(r.toJSONString()));
		jobj.put("dueDate", d.getTime());
		jobj.put("client", new JSONObject(c.toJSONString()));
		Request r=RequestStorer.getRequestByJSONObject(jobj);
		assertEquals(r.toString(),request.toString());
	}
	
	@Test
	public void test_04()
	{
		JSONObject _jobj=RequestStorer.putRequestToJSONObject(request);
		JSONObject jobj=new JSONObject();
		jobj.put("rentable", new JSONObject(r.toJSONString()));
		jobj.put("dueDate", d.getTime());
		jobj.put("client", new JSONObject(c.toJSONString()));
		assertEquals(_jobj.toString(),jobj.toString());
	}
	
	@Test
	public void test_05() throws IOException
	{
		rs.readFromJson("test/testRequestJSON.json");
		System.out.println("-------------");
		System.out.println(rs.getList().get(0).toString());
		assertEquals(rs.getList().get(0).toString(),request.toString());
	}
	
	@Test
	public void test_06() throws IOException
	{
		rs.writeToJson("test/testRequestJSON.json");;
		rs.readFromJson("test/testRequestJSON.json");
		assertEquals(rs.getList().get(0).toString(),request.toString());
	}
}
