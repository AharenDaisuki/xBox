package test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.json.JSONObject;
import org.junit.Test;

import data.*;

public class TestClientStorer {
	
	ClientStorer cs=ClientStorer.getInstance();

	@Test
	public void test_01()
	{
		JSONObject jobj=new JSONObject();
		jobj.put("password", "123456");
		jobj.put("type", "staff");
		jobj.put("email", "123@abc.com");
		jobj.put("phoneNo", "123");
		Client c=new ClientStaff("123@abc.com","123","123456");
		assertEquals(c.toString(),ClientStorer.getClientByJSONObject(jobj).toString());
	}
	
	@Test
	public void test_02()
	{
		JSONObject jobj=new JSONObject();
		jobj.put("password", "123456");
		jobj.put("type", "student");
		jobj.put("email", "123@abc.com");
		jobj.put("phoneNo", "123");
		Client c=new ClientStudent("123@abc.com","123","123456");
		assertEquals(c.toString(),ClientStorer.getClientByJSONObject(jobj).toString());
	}
	
	@Test
	public void test_03()
	{
		JSONObject jobj=new JSONObject();
		jobj.put("password", "123456");
		jobj.put("type", "asnie");
		jobj.put("email", "123@abc.com");
		jobj.put("phoneNo", "123");
		assertEquals(null,ClientStorer.getClientByJSONObject(jobj));
	}
	
	@Test
	public void test_04()
	{
		Client c=new ClientStaff("123@abc.com","123","123456");
		JSONObject _jobj=ClientStorer.putClientToJSONObject(c);
		JSONObject jobj=new JSONObject();
		jobj.put("password", "123456");
		jobj.put("type", "staff");
		jobj.put("email", "123@abc.com");
		jobj.put("phoneNo", "123");
		assertEquals(_jobj.toString(),jobj.toString());
	}
	
	@Test
	public void test_05()
	{
		Client c=new ClientStudent("123@abc.com","123","123456");
		JSONObject _jobj=ClientStorer.putClientToJSONObject(c);
		JSONObject jobj=new JSONObject();
		jobj.put("password", "123456");
		jobj.put("type", "student");
		jobj.put("email", "123@abc.com");
		jobj.put("phoneNo", "123");
		assertEquals(_jobj.toString(),jobj.toString());
	}
	
	@Test
	public void test_06()
	{
		class ClientTemp extends Client{

			public ClientTemp(String email_, String phoneNo_, String password_) {
				super(email_, phoneNo_, password_);
			}

			@Override
			public double getDiscount() {
				return 0;
			}

			@Override
			public int getMaxBorrowedCount() {
				return 0;
			}

            @Override
            public int getBorrowCount() {
                // TODO Auto-generated method stub
                return 0;
            }

            @Override
            public void changeBorrowedCount(int number) {     
            }
		}
		Client c=new ClientTemp("1","1","1");
		c.getDiscount();
		c.getMaxBorrowedCount();
		assertEquals(null,ClientStorer.putClientToJSONObject(c));
	}
	
	@Test
	public void test_07() throws IOException
	{
		cs.readFromJson("src/test/testClientJSON.json");
		assertEquals(cs.getList().get(0).toJSONString(), "{\"email\":\"123@abc.com\",\"phoneNo\":\"1234\",\"password\":\"123\",\"type\":\"staff\"}");
	}
	
	@Test
	public void test_08() throws IOException
	{
		cs.writeToJson("src/test/testClientJSON.json");
		cs.readFromJson("src/test/testClientJSON.json");
		assertEquals(cs.getList().get(0).toJSONString(), "{\"email\":\"123@abc.com\",\"phoneNo\":\"1234\",\"password\":\"123\",\"type\":\"staff\"}");
	}
}
