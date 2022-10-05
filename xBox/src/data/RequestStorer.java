package data;

import java.util.ArrayList;

public class RequestStorer{

	private ArrayList<Request> requestList;
	private static RequestStorer storer=new RequestStorer();
	
	private RequestStorer()
	{
		requestList=new ArrayList<>();
	}

	public static RequestStorer getInstance()
	{
		return storer;
	}
	public ArrayList<Request> getList()
	{
		return requestList;
	}
}
