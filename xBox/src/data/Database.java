package data;

import java.io.*;

public class Database {
    private static Database database=new Database();
    
    private RentableStorer rentableStorer;
    private RecordStorer recordStorer; 
    private ClientStorer clientStorer;
    private RequestStorer requestStorer;

    private Database(){
        rentableStorer=RentableStorer.getInstance();
        recordStorer=RecordStorer.getInstance();
        clientStorer=ClientStorer.getInstance();
        requestStorer=RequestStorer.getInstance();
    }

    public static Database getInstance(){
        return database;
    }

    public void initialize() throws IOException
    {
        rentableStorer.readFromJson();
        recordStorer.readFromJson();
        clientStorer.readFromJson();
        requestStorer.readFromJson();
    }

    public void storeUp() throws IOException
    {
        rentableStorer.writeToJson();
        recordStorer.writeToJson();
        clientStorer.writeToJson();
        requestStorer.writeToJson();
    }
    // save in json
}
