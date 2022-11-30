package test;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;

import data.*;

public class TestRequestManager {
	private RequestStorer rs=RequestStorer.getInstance();
	private RequestManager rm=RequestManager.getInstance();
	private Client c=new ClientStaff("123@abc.com","1234","123");
	private Rentable r=new Bag("001",new RentableStatusAvailable());
	private Date d=new Date(114514);
	
	private Request request=new Request(c,r,d);
	
	@Test
	public void test_01()
	{
		rm.newRequest(request);
		assertEquals(rs.getList().contains(request),true);
	}
	
	@Test
	public void test_02()
	{
		rm.removeRequest(request);
		assertEquals(rs.getList().contains(request),false);
	}
}
