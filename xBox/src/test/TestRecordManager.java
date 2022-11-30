package test;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;

import data.*;
import data.Record;

public class TestRecordManager {
	private RecordStorer rs=RecordStorer.getInstance();
	private RecordManager rm=RecordManager.getInstance();
	private Client c=new ClientStaff("123@abc.com","1234","123");
	private Rentable r=new Bag("001",new RentableStatusAvailable());
	private Date d=new Date(114514);
	
	private Record record=new Record(c,r,d,false);
	
	@Test
	public void test_01()
	{
		rm.insert(record);
		assertEquals(rs.getList().contains(record),true);
	}
	
	@Test
	public void test_02()
	{
		rm.delete(record);
		assertEquals(rs.getList().contains(record),false);
	}
}
