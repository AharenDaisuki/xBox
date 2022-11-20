package test;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;

import data.*;

public class TestRequest {
	private Client c=new ClientStaff("123@abc.com","1234","123");
	private Rentable r=new Bag("001",new RentableStatusAvailable());
	private Date d=new Date(114514);
	
	private Request request=new Request(c,r,d);
	
	@Test
	public void test_01()
	{
		request.setClient(c);
		request.setRentable(r);
		assertEquals(request.getClient().toString(),c.toString());
		assertEquals(request.getDue().getTime(),d.getTime());
		assertEquals(request.getRentable().toString(),r.toString());
	}
	
	@Test
	public void test_02()
	{
		assertEquals(request.toString(),"BAG001         123@abc.com              1970-01-01");
	}
	
	@Test
	public void test_03()
	{
		assertEquals(request.toJSONString(),"{\"client\":{\"email\":\"123@abc.com\",\"phoneNo\":\"1234\",\"password\":\"123\",\"type\":\"staff\"},\"rentable\":{\"id\":\"001\",\"status\":{\"status\":\"Available\"},\"type\":\"BAG\"},\"dueDate\":114514}");
	}
}
