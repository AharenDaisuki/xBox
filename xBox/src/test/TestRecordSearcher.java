package test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;

import data.*;
import data.Record;

public class TestRecordSearcher {
	private RecordStorer rs=RecordStorer.getInstance();
	private RecordManager rm=RecordManager.getInstance();
	private RecordSearcher rsch=RecordSearcher.getInstance();
	private Client c=new ClientStaff("123@abc.com","1234","123");
	private Rentable r=new Bag("001",new RentableStatusAvailable());
	private Date d=new Date(114514);
	
	private Record record=new Record(c,r,d,false);
	
	public TestRecordSearcher()
	{
		rm.insert(record);
	}
	
	@Test
	public void test_01()
	{
		Record r=rsch.searchByKeyword("BAG001");
		assertEquals(r.toString(),record.toString());
	}
	
	@Test
	public void test_02()
	{
		Record r=rsch.searchByKeyword("Ba001");
		assertEquals(r,null);
	}
	
	@Test
	public void test_03()
	{
		ArrayList<Record> list=rsch.searchAllByKeyword("123@abc.com");
		assertEquals(list.get(0).toString(),record.toString());
	}
	
	@Test
	public void test_04()
	{
		ArrayList<Record> list=rsch.searchAllByKeyword("123@ac.com");
		assertEquals(list.size(),0);
	}
	
	@Test
	public void test_05()
	{
		ArrayList<Record> list=rsch.searchAllByKeyword(d);
		assertEquals(list.get(0).toString(),record.toString());
	}
	
	@Test
	public void test_06()
	{
		ArrayList<Record> list=rsch.searchAllByKeyword(new Date(114));
		assertEquals(list.size(),0);
	}
	
	@Test
	public void test_07()
	{
		ArrayList<Record> list=rsch.searchAll();
		assertEquals(list.get(0).toString(),record.toString());
	}
}
