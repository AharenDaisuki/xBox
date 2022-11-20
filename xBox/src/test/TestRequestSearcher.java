package test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;

import data.*;

public class TestRequestSearcher {
	private RequestStorer rs=RequestStorer.getInstance();
	private RequestManager rm=RequestManager.getInstance();
	private RequestSearcher rsch=RequestSearcher.getInstance();
	private Client c=new ClientStaff("123@abc.com","1234","123");
	private Rentable r=new Bag("001",new RentableStatusAvailable());
	private Date d=new Date(114514);
	
	private Request request=new Request(c,r,d);
	
	public TestRequestSearcher()
	{
		rm.newRequest(request);
	}
	
	@Test
	public void test_01()
	{
		Request r=rsch.searchByKeyword("BAG001");
		assertEquals(r.toString(),request.toString());
	}
	
	@Test
	public void test_02()
	{
		Request r=rsch.searchByKeyword("Ba001");
		assertEquals(r,null);
	}
	
	@Test
	public void test_03()
	{
		ArrayList<Request> list=rsch.searchAllByKeyword("123@abc.com");
		assertEquals(list.get(0).toString(),request.toString());
	}
	
	@Test
	public void test_04()
	{
		ArrayList<Request> list=rsch.searchAllByKeyword("123@ac.com");
		assertEquals(list.size(),0);
	}
	
	@Test
	public void test_07()
	{
		ArrayList<Request> list=rsch.searchAll();
		assertEquals(list.get(0).toString(),request.toString());
	}
}
