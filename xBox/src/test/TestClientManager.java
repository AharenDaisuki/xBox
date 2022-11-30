package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import data.*;

public class TestClientManager {
	private ClientManager cm=ClientManager.getInstance();
	private ClientStorer cs=ClientStorer.getInstance();
	private Client c=new ClientStaff("123@abc.com","1234","123");
	
	@Test
	public void test_01()
	{
		cm.insert(c);
		boolean res=cs.getList().contains(c);
		assertEquals(res,true);
	}
	
	@Test
	public void test_02()
	{
		cm.delete(c);
		boolean res=cs.getList().contains(c);
		assertEquals(res,false);
	}
}
