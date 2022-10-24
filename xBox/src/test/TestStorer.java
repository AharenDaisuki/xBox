package test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;

import data.Client;
import data.ClientStorer;
import data.RecordStorer;
import data.Rentable;
import data.RentableStorer;
import data.Request;
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
	@Test
	public void testRequestStorerReadingFromJson() throws IOException
	{
		RequestStorer s=RequestStorer.getInstance();
		s.readFromJson();
		ArrayList<Request> list=s.getList();
		for(Request c:list)
			System.out.println(c.toJSONString());
	}
}
