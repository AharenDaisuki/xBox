package test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import data.*;

public class TestClientSearcher {
	private ClientStorer cs=ClientStorer.getInstance();
	private ClientSearcher csch=ClientSearcher.getInstance();
	private ClientManager cm=ClientManager.getInstance();
	
	private Client c=new ClientStaff("123@abc.com","1234","123");
	
	public TestClientSearcher()
	{
		cm.insert(c);
	}
	
	@Test
	public void test_01()
	{
		ArrayList<Client> list=csch.searchAll();
		assertEquals(list.get(0).toJSONString(),c.toJSONString());
	}
	
	@Test
	public void test_02()
	{
		Client _c=csch.searchByKeyword("123@abc.com");
		assertEquals(_c.toJSONString(),c.toJSONString());
	}
	
	@Test
	public void test_03()
	{
		Client _c=csch.searchByKeyword("123@ac.com");
		assertEquals(_c,null);
	}
}
