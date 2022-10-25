package test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;

import org.junit.Test;

import data.Box;
import data.Client;
import data.ClientManager;
import data.ClientStorer;
import data.ClientStudent;
import data.Day;
import data.RecordStorer;
import data.Rentable;
import data.RentableManager;
import data.RentableStorer;
import data.RentableStatusOccupied;
import data.Request;
import data.RequestManager;
import data.RequestStorer;
import data.Record;

public class TestStorer {
	
//	@Test
//	public void testClientStorerReadingFromJson() throws IOException
//	{
//		ClientStorer s=ClientStorer.getInstance();
//		s.readFromJson();
//		ArrayList<Client> list=s.getList();
//		for(Client c:list)
//			System.out.println(c.toJSONString());
//	}
//	@Test
//	public void testRentableStorerReadingFromJson() throws IOException
//	{
//		RentableStorer s=RentableStorer.getInstance();
//		s.readFromJson();
//		HashMap<String,ArrayList<Rentable>> list=s.getManager();
//		for(Rentable c: list.get("BAG"))
//			System.out.println(c.toJSONString());
//		for(Rentable c: list.get("BOX"))
//			System.out.println(c.toJSONString());
//	}
//	@Test
//	public void testRecordStorerReadingFromJson() throws IOException
//	{
//		RecordStorer s=RecordStorer.getInstance();
//		s.readFromJson();
//		ArrayList<Record> list=s.getList();
//		for(Record c:list)
//			System.out.println(c.toJSONString());
//	}
//	@Test
//	public void testRequestStorerReadingFromJson() throws IOException
//	{
//		RequestStorer s=RequestStorer.getInstance();
//		s.readFromJson();
//		ArrayList<Request> list=s.getList();
//		for(Request c:list)
//			System.out.println(c.toJSONString());
//	}
//	@Test
//	public void testClientStorerWritingtoJson() throws IOException
//	{
//		ClientStorer s=ClientStorer.getInstance();
//		ClientManager m=ClientManager.getInstance();
//		Client cs=new ClientStudent("abc@123.com","12345678","123456"); 
//		m.insert(cs);
//		s.writeToJson();
//		ArrayList<Client> list=s.getList();
//		for(Client c:list)
//			System.out.println(c.toJSONString());
//	}
//	@Test
//	public void testRentableStorerWritingtoJson() throws IOException
//	{
//		RentableStorer s=RentableStorer.getInstance();
//		RentableManager m=RentableManager.getInstance();
//		Client cs=new ClientStudent("abc@123.com","12345678","123456");
//		Rentable r=new Box("123",new RentableStatusOccupied(new Date(),cs)); 
//		m.addNewRentable(r);
//		s.writeToJson();
//		ArrayList<Rentable> list=s.getList("BOX");
//		for(Rentable c:list)
//			System.out.println(c.toJSONString());
//	}
	@Test
	public void testRequestStorerWritingtoJson() throws IOException
	{
		RequestStorer s=RequestStorer.getInstance();
		RequestManager m=RequestManager.getInstance();
		Client cs=new ClientStudent("abc@123.com","12345678","123456"); 
		Rentable b=new Box("123",new RentableStatusOccupied(new Date(),cs)); 
		Request r=new Request(cs,b,new Date());
		m.newRequest(r);
		s.writeToJson();
		ArrayList<Request> list=s.getList();
		for(Request c:list)
			System.out.println(c.toJSONString());
	}
}
