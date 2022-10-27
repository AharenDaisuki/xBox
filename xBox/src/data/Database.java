package data;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

import debug.DebugConfig;

public class Database {
    private static Database database = new Database();
    
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
        // rentableStorer.readFromJson();
        // recordStorer.readFromJson();
        // clientStorer.readFromJson();
        // requestStorer.readFromJson();
        rentableStorer.readJson();
    }

    public void storeUp()
    {
        rentableStorer.writeToJson();
        recordStorer.writeToJson();
        clientStorer.writeToJson();
        requestStorer.writeToJson();
    }
    // save in json
}
