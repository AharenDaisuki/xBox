package test;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;

import data.*;
import data.Record;

public class TestRecord {
	private Client client=new ClientStaff("123@abc.com","1234","123");
	private Rentable rentable=new Bag("001",new RentableStatusAvailable());
	private Date due=new Date(114514);
	private boolean is_paid=false;
	
	private Record r1=new Record(client,rentable,due);
	private Record r2=new Record(client,rentable,due,is_paid);
	
	@Test
	public void test_01()
	{
		Record r=new Record();
		r.setClient(client);
		r.setDue(due);
		r.setPaymentStatus(is_paid);
		r.setRentable(rentable);
		assertEquals(r.getClient().toString(),client.toString());
		assertEquals(r.getRentable().toString(),rentable.toString());
		assertEquals(r.getDue().toString(),due.toString());
		assertEquals(r.getPaymentStatus(),is_paid);
	}
	
	@Test
	public void test_02()
	{
		assertEquals(r1.toString(),"BAG001         unpaid         123@abc.com              1970-01-01");
	}
	
	@Test
	public void test_03()
	{
		r1.setPaymentStatus(true);
		assertEquals(r1.toString(),"BAG001         paid           123@abc.com              1970-01-01");
	}
	
	@Test
	public void test_04()
	{
		assertEquals(r1.toJSONString(),"{\"client\":{\"email\":\"123@abc.com\",\"phoneNo\":\"1234\",\"password\":\"123\",\"type\":\"staff\"},\"rentable\":{\"id\":\"001\",\"status\":{\"status\":\"Available\"},\"type\":\"BAG\"},\"dueDate\":114514,\"isPaid\":false}");
	}
}
