package test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Date;

import org.junit.Test;

import data.*;
import data.Record;

public class TestDatabase {
	private Client c=new ClientStaff("123@abc.com","1234","123");
	private Rentable r=new Bag("001",new RentableStatusAvailable());
	private Date d=new Date(114514);
	
	private RentableStorer rentableStorer=RentableStorer.getInstance();
    private RecordStorer recordStorer=RecordStorer.getInstance();
    private ClientStorer clientStorer=ClientStorer.getInstance();
    private RequestStorer requestStorer=RequestStorer.getInstance();
	
    private Database database=Database.getInstance();
    
    @Test
    public void test_01() throws IOException
    {
    	String[] str=new String[]{"src/test/testRentableJSON.json","src/test/testRecordJSON.json","src/test/testClientJSON.json","src/test/testRequestJSON.json"};
    	database.initialize(str);
    	System.out.println(recordStorer.getList().size());
    	assertEquals(rentableStorer.getList("BAG").get(0).toString(),r.toString());
    	assertEquals(recordStorer.getList().get(0).toString(),(new Record(c,r,d,false)).toString());
    	assertEquals(clientStorer.getList().get(0).toString(),c.toString());
    	assertEquals(requestStorer.getList().get(0).toString(),(new Request(c,r,d)).toString());
    }
    
    @Test
    public void test_02() throws IOException
    {
    	String[] str=new String[]{"src/test/testRentableJSON.json","src/test/testRecordJSON.json","src/test/testClientJSON.json","src/test/testRequestJSON.json"};
    	database.storeUp(str);
    	database.initialize(str);
    	assertEquals(rentableStorer.getList("BAG").get(0).toString(),r.toString());
    	assertEquals(recordStorer.getList().get(0).toString(),(new Record(c,r,d,false)).toString());
    	assertEquals(clientStorer.getList().get(0).toString(),c.toString());
    	assertEquals(requestStorer.getList().get(0).toString(),(new Request(c,r,d)).toString());
    }
}
